package com.szbf.service;


import com.szbf.constant.CommandEnum;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatService.java
 * @Description 设备交互业务
 */
public interface StorexService {
    String commonOperation(String command) throws Exception;
    String commonOperation(CommandEnum command) throws Exception;
    String commonOperation(String data, CommandEnum command) throws Exception;
    void whileStatus() throws Exception;
    void importPlate(String position, String level) throws Exception;
    void exportPlate(String position, String level) throws Exception;
    void setXFerPlate(String position, String level) throws Exception;
    void getXFerPlate(String position, String level) throws Exception;
    void SwapPlate(String position_1, String level_1, String position_2, String level_2) throws Exception;
    void AutoShaker() throws Exception;
    void ShakerTrue() throws Exception;
}
