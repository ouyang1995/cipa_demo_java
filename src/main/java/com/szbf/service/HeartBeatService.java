package com.szbf.service;

import com.szbf.pojo.heart.Info.HeartBeatInfo;
import com.szbf.util.Result;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatService.java
 * @Description 心跳监测功能
 * @createTime 2022年07月21日 14:56:00
 */
public interface HeartBeatService {
    Result<HeartBeatInfo> heartbeat() throws Exception;
}
