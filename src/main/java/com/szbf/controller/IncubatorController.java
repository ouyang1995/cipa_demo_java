package com.szbf.controller;

import com.szbf.pojo.Interface.*;
import com.szbf.pojo.heart.Info.HeartBeatInfo;
import com.szbf.pojo.method.RegisterInfo;
import com.szbf.service.HeartBeatService;
import com.szbf.service.serviceimpl.IncubatorHandler;
import com.szbf.util.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j

public class IncubatorController {
    @Autowired
    private IncubatorHandler incubatorHandler;
    @Autowired
    private HeartBeatService heartBeatService;
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description Function接口
     */

    @ResponseBody
    @RequestMapping(value = "/Function", method = RequestMethod.POST)
    public Result Function(@RequestBody FunctionData functionData) throws ClassNotFoundException {
        log.info("成功接受信息");
        incubatorHandler.incubatorFunction(functionData);
        return Result.success();
    }
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description Set接口
     */

    @ResponseBody
    @RequestMapping(value = "/Set", method = RequestMethod.POST)
    public Result<Finish> Set(@RequestBody List<SetData> setData) throws Exception {
        log.info("成功接受信息");
        return Result.success(incubatorHandler.incubatorSet(setData));
    }
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description Get接口
     */

    @ResponseBody
    @RequestMapping(value = "/Get", method = RequestMethod.GET)
    public Result<List<GetReturn>> Get() throws Exception {
        log.info("成功接受信息");
        return Result.success(incubatorHandler.incubatorGet());
    }

    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description 进出板接口
     */

    @ResponseBody
    @RequestMapping(value = "/EnterAndExit", method = RequestMethod.POST)
    public Result<Finish> EnterAndExit(@RequestBody EnterOrExitData enterOrExitData) throws Exception {
        log.info("成功接受信息");
        return Result.success(incubatorHandler.incubatorEnterAndExit(enterOrExitData));
    }
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description 调试接口
     */

    @ResponseBody
    @RequestMapping(value = "/Operation", method = RequestMethod.POST)
    public Result Operation(@RequestBody OperationData operationData) throws Exception {
        try {
            log.info("成功接受信息");
            incubatorHandler.incubatorOperation(operationData);
            return Result.success();
        }catch (Exception e){
            return Result.failed(e.getMessage());
        }
    }
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description 注册信息接口
     */

    @ResponseBody
    @RequestMapping(value = "/Info", method = RequestMethod.GET)
    public Result<RegisterInfo> Info() throws Exception {
        log.info("成功接受信息");
        return Result.success(incubatorHandler.incubatorInfo());
    }
    /**
     * @author 刘荣
     * @version 1.0.1
     * @Description 心跳监测
     */

    @ResponseBody
    @RequestMapping(value = "/HeartBeat", method = RequestMethod.GET)
    public Result<HeartBeatInfo> HeartBeat() throws Exception {
        log.info("成功接受信息");
        return heartBeatService.heartbeat();
    }

}