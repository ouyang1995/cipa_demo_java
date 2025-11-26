package com.szbf.service;


import com.szbf.pojo.Interface.EnterOrExitData;
import com.szbf.pojo.Interface.OperationData;
import com.szbf.pojo.Interface.FunctionData;
import com.szbf.pojo.Interface.SetData;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatService.java
 * @Description 设备功能业务
 */
public interface StorexMethodService {
    //debug
    void incubateDebug(OperationData operationData) throws Exception;
    void openCommunicationDebug(OperationData operationData) throws Exception;
    void closeCommunicationDebug(OperationData operationData) throws Exception;
    void importPlateDebug(OperationData operationData) throws Exception;
    void exportPlateDebug(OperationData operationData) throws Exception;
    void openGateDebug(OperationData operationData) throws Exception;
    void closeGateDebug(OperationData operationData) throws Exception;
    void resetInitDebug(OperationData operationData) throws Exception;
    //function
    void incubate(FunctionData functionData) throws Exception;
    void openCommunication(FunctionData functionData) throws Exception;
    void closeCommunication(FunctionData functionData) throws Exception;
    void importPlate(FunctionData functionData) throws Exception;
    void exportPlate(FunctionData functionData) throws Exception;
    void resetInit(FunctionData functionData) throws Exception;
    //setter,getter
    void shakerSpeed(SetData setData) throws Exception;
    String readShakerStatus() throws Exception;
    String readShakerSpeed() throws Exception;
    void setTemperature(SetData setData) throws Exception;
    String readTemperature() throws Exception;
    String readActualTemperature() throws Exception;
    void setHumidity(SetData setData) throws Exception;
    String readSetHumidity() throws Exception;
    String readActualHumidity() throws Exception;
    void setCO2(SetData setData) throws Exception;
    String readSetCO2() throws Exception;
    String readActualCO2() throws Exception;
    void set02(SetData setData) throws Exception;
    String readSetO2() throws Exception;
    String readActualO2() throws Exception;
    void setN2(SetData setData) throws Exception;
    String readSetN2() throws Exception;
    String readActualN2() throws Exception;
    void isShaker(OperationData operationData) throws Exception;
    //inAndOut
    void importPlateInAndOut(EnterOrExitData enterOrExitData) throws Exception;
    void exportPlateInAndOut(EnterOrExitData enterOrExitData) throws Exception;
    //simulation
    void simulaitionIncubate(FunctionData functionData) throws InterruptedException;

    void simulaitionOpenCommunication(FunctionData functionData) throws InterruptedException;

    void simulaitionCloseCommunication(FunctionData functionData) throws InterruptedException;

    void simulaitionImportPlate(FunctionData functionData) throws InterruptedException;

    void simulaitionExportPlate(FunctionData functionData) throws InterruptedException;
}
