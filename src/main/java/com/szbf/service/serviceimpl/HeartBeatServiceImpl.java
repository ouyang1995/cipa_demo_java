package com.szbf.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.szbf.common.ServiceException;
import com.szbf.config.TCPRunner;
import com.szbf.constant.CommandEnum;
import com.szbf.constant.HeartBeatStatusEnum;
import com.szbf.constant.ServiceExceptionEnum;
import com.szbf.pojo.Interface.OperationData;
import com.szbf.pojo.heart.Info.HeartBeatInfo;
import com.szbf.pojo.heart.health;
import com.szbf.service.HeartBeatService;
import com.szbf.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

import static com.szbf.service.serviceimpl.IncubatorHandler.IResource;
import static com.szbf.service.serviceimpl.IncubatorHandler.initResource;
import static com.szbf.service.serviceimpl.IncubatorSerialImplement.COMMAND_SUFFIX;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatServiceImpl.java
 * @Description TODO
 * @createTime 2022年07月21日 14:58:00
 */
@Service
@Slf4j
public class HeartBeatServiceImpl  implements HeartBeatService {
    @Value("${server.port}")
    public Integer port;
    @Value("${cfg.tcp-server-ip}")
    private String tcpServerIp;
    @Value("${cfg.tcp-server-port}")
    private String tcpServerPort;

    @Override
    public Result<HeartBeatInfo> heartbeat() throws Exception {
        //客户端状态检测
        String ip = "http://127.0.0.1:" + port + "/actuator/health";
        String s = HttpUtil.doGet(ip);
        health controller_health = JSON.parseObject(s, health.class);
        //培养箱状态检测
        health storex_health = storexHeartBeat();
        //心跳监测状态处理
        HeartBeatInfo heartBeatInfo=new HeartBeatInfo();
        heartBeatInfo.setHeartBeatTime(new Date());
        heartBeatInfo.setHeartBeatStatus(HeartBeatStatusEnum.Normal.getCode());
        if (storex_health.getStatus().equals("DOWN")){
            heartBeatInfo.setHeartBeatStatus(HeartBeatStatusEnum.EquipmentError.getCode());
        }
        if (controller_health.getStatus().equals("DOWN")){
            heartBeatInfo.setHeartBeatStatus(HeartBeatStatusEnum.DriverAbnormal.getCode());
        }
        return Result.success(heartBeatInfo);
    }
    public health storexHeartBeat() throws Exception {
            try {
                if (initResource.tryAcquire()) {
                    if (TCPRunner.isInitSuccess) {
                        if (IResource.tryAcquire()) {
                            Thread.sleep(300);
                            byte[] data = HextoByte.hexToByteArray(CommandEnum.ERROR_FLAG.getInstruction() + COMMAND_SUFFIX);
                            TCPClient.Write(data);
                            Thread.sleep(1000);
                                    String read = TCPClient.Read();
                                    if (read.contains("0")) {
                                        log.info("设备心跳监测正常");
                                    } else if (read.contains("1")) {
                                        throw new Exception("设备异常");
                                    }
                                    IResource.release();
                                    initResource.release();
                                } else {
                                    initResource.release();
                                }
                            } else {
                                initResource.release();
                                log.error("心跳监测错误");
                                return new health("DOWN", null);
                    }
                }
                return new health("UP", null);
            }catch (Exception e) {
                initResource.release();
                IResource.release();
                log.error("心跳监测错误");
                return new health("DOWN", null);
            }
    }
}
