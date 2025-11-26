package com.szbf.config;

import com.alibaba.fastjson.JSON;
import com.szbf.constant.CommandEnum;
import com.szbf.pojo.PlateMethod;
import com.szbf.service.StorexMethodService;
import com.szbf.service.StorexService;
import com.szbf.service.serviceimpl.IncubatorHandler;
import com.szbf.util.TCPClient;


import com.szbf.util.TxtReadline;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class TCPRunner implements ApplicationRunner {

    @Value("${cfg.shakermanagement}")
    private String shakermanagement;
    public static SerialPort serialPort;
    @Autowired
    StorexService storexService;
    @Autowired
    @Qualifier("serialPortEventListener")
    private SerialPortEventListener serialPortEventListener;

    @Autowired
    private StorexMethodService storexMethodService;
    public static boolean isInitSuccess;
    @Value("${cfg.tcp-server-ip}")
    private String tcpServerIp;
    @Value("${cfg.tcp-server-port}")
    private String tcpServerPort;

    @Autowired
    private IncubatorHandler incubatorHandler;

    //This is used to pre-configure your startup items
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("准备开始初始化");
            IncubatorHandler.initResource.tryAcquire();
            TCPClient.connect(tcpServerIp,Integer.parseInt(tcpServerPort));
            Thread.sleep(1000);
            storexMethodService.openCommunication(null);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.RESET);
            log.info(CommandEnum.RESET.getDesc()+"is successful");
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.SOFT_RESET);
            log.info(CommandEnum.SOFT_RESET.getDesc()+"is successful");
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.INITIALIZE);
            log.info(CommandEnum.INITIALIZE.getDesc()+"is successful");
            storexService.whileStatus();
            Thread.sleep(300);
            log.info("Incubator打开成功...");
            log.info("设置震动速度为200...");
            Thread.sleep(500);

            int setValueInt=20;
            storexService.commonOperation(String.valueOf(setValueInt),CommandEnum.SET_SHAKER_SPEED);
            Thread.sleep(300);
            int i=0;
            while (true){
                if (storexService.commonOperation(CommandEnum.READ_SHAKER_SPEED).contains(String.valueOf(setValueInt))){
                    log.info("设置震动速度为200成功");
                    break;}
                else {
                    log.info("正在验证震动速度是否修改成功");
                    Thread.sleep(1000);
                }
                if (i==20){
                    log.info("验证震动速度超时");
                    throw new Exception("验证震动速度超时");
                }
                i++;
            }

            Thread.sleep(10000);
            if (shakermanagement.contains("true")){
                storexService.ShakerTrue();
                log.info("set Shaker is true");
            }
            if (shakermanagement.contains("auto")){
                storexService.AutoShaker();
                log.info("set Shaker is auto");
            }
            isInitSuccess=true;
            IncubatorHandler.initResource.release();
        }catch (Exception e){
            isInitSuccess=false;
            IncubatorHandler.initResource.release();
            System.out.println(e.getMessage());
        }
    }
}
