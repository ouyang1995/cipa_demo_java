package com.szbf.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szbf.common.ThrowingConsumer;
import com.szbf.common.ServiceException;
import com.szbf.config.TCPRunner;
import com.szbf.constant.*;
import com.szbf.pojo.Incubate;
import com.szbf.pojo.Interface.*;
import com.szbf.pojo.PlateMethod;
import com.szbf.pojo.method.*;
import com.szbf.service.StorexMethodService;
import com.szbf.service.StorexService;
import com.szbf.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static com.szbf.service.serviceimpl.IncubatorSerialImplement.COMMAND_SUFFIX;


@Service
@Component
@Slf4j
public class IncubatorHandler {

    @Autowired
    private StorexMethodService storexMethodService;
    @Autowired
    private Rest rest;

    @Autowired
    StorexService storexService;
    private Map<String, ThrowingConsumer<FunctionData>> functionMap = new HashMap<>();
    private Map<String, ThrowingConsumer<FunctionData>> simulationMap = new HashMap<>();
    private Map<String, ThrowingConsumer<SetData>> setterMap = new HashMap<>();
    private Map<String,String> getterMap= new HashMap<>();
    private Map<String, ThrowingConsumer<EnterOrExitData>> inAndOutMap = new HashMap<>();
    private Map<String,ThrowingConsumer<OperationData>> debugMap = new HashMap<>();

    private Integer getScheduled=0;

    public static final Semaphore IResource =new Semaphore(1,true);
    public static final Semaphore initResource =new Semaphore(1,true);
    private static List<GetReturn> returnList=new ArrayList<>();
    public static boolean isSet= true;

    @Value("${cfg.shake-speed}")
    private String shake_speed;
    @Value("${cfg.tcp-server-ip}")
    private String tcpServerIp;
    @Value("${cfg.tcp-server-port}")
    private String tcpServerPort;
    @Value("${cfg.temperature-min}")
    private String tem_min;
    @Value("${cfg.temperature-max}")
    private String tem_max;
    @PostConstruct
    public void Debuginit(){
        debugMap.put(MethodName.ActivateShaker, storexMethodService :: isShaker);
        debugMap.put(MethodName.Incubate, storexMethodService :: incubateDebug);
        debugMap.put(MethodName.OpenCommunication, storexMethodService :: openCommunicationDebug);
        debugMap.put(MethodName.CloseCommunication, storexMethodService :: closeCommunicationDebug);
        debugMap.put(MethodName.ResetInit, storexMethodService :: resetInitDebug);
        debugMap.put(MethodName.OpenGate,storexMethodService::openGateDebug);
        debugMap.put(MethodName.CloseGate,storexMethodService::closeGateDebug);
        debugMap.put(MethodName.ImportPlate,storexMethodService::importPlateDebug);
        debugMap.put(MethodName.ExportPlate,storexMethodService::exportPlateDebug);
    }


    @PostConstruct
    public void InAndOutinit(){
        inAndOutMap.put(MethodName.ImportPlateOPCU,storexMethodService :: importPlateInAndOut);
        inAndOutMap.put(MethodName.ExportPlateOPCU,storexMethodService::exportPlateInAndOut);
    }
    @PostConstruct
    public void Setterinit(){
        setterMap.put(MethodName.ShakerSpeed,storexMethodService :: shakerSpeed);
        setterMap.put(MethodName.SetO2,storexMethodService ::set02);
        setterMap.put(MethodName.SetCO2,storexMethodService ::setCO2);
        setterMap.put(MethodName.SetHumidity,storexMethodService ::setHumidity);
        setterMap.put(MethodName.SetN2,storexMethodService ::setN2);
        setterMap.put(MethodName.SetTemperature,storexMethodService ::setTemperature);
    }
    @PostConstruct
    public void init(){
        functionMap.put(MethodName.Incubate, storexMethodService :: incubate);
    }
    @PostConstruct
    public void Simulationinit(){
        simulationMap.put(MethodName.Incubate, storexMethodService :: simulaitionIncubate);
    }

    public boolean Reconnect() throws Exception {
        int i=0;
        while (true) {
            try {
                if (i<=300) {
                    Thread.sleep(1000);
                    i++;
                    TCPClient.sock.close();
                    TCPClient.connect(tcpServerIp, Integer.parseInt(tcpServerPort));
                    return true;
                }else {
                    return false;
                }
            }
             catch (Exception e) {
                // TODO: handle exception
                log.info("TCP尝试重连失败,次数:"+i);
            }
        }
    }

    public List<GetReturn> incubatorGet() throws Exception {
        try {
            try {
                log.info("get-准备获取串口资源(如被占用则不加入信号量)...");
                if (initResource.tryAcquire()) {
                    if (TCPRunner.isInitSuccess) {
                        if (IResource.tryAcquire()) {
                            log.info("get-获取串口资源完成...");
                            getScheduled++;
                            if (isSet||getScheduled==30) {
                                returnList = new ArrayList<>();
                                getterMap.put(MethodName.ReadActualCO2, storexMethodService.readActualCO2());
                                getterMap.put(MethodName.ReadActualHumidity, storexMethodService.readActualHumidity());
                                getterMap.put(MethodName.ReadActualN2, storexMethodService.readActualN2());
                                getterMap.put(MethodName.ReadActualO2, storexMethodService.readActualO2());
                                getterMap.put(MethodName.ReadActualTemperature, storexMethodService.readActualTemperature());
                                getterMap.put(MethodName.ReadSetCO2, storexMethodService.readSetCO2());
                                getterMap.put(MethodName.ReadSetHumidity, storexMethodService.readSetHumidity());
                                getterMap.put(MethodName.ReadSetN2, storexMethodService.readSetN2());
                                getterMap.put(MethodName.ReadSetO2, storexMethodService.readSetO2());
                                getterMap.put(MethodName.ReadTemperature, storexMethodService.readTemperature());
                                getterMap.put(MethodName.ReadShakerSpeed, storexMethodService.readShakerSpeed());
                                getterMap.put(MethodName.ReadShakerStatus, storexMethodService.readShakerStatus());
                                getterMap.forEach((key, value) -> {
                                    GetReturn getReturn = new GetReturn();
                                    getReturn.setGetName(key);
                                    getReturn.setGetValue(value);
                                    returnList.add(getReturn);
                                });
                                isSet = false;
                                getScheduled=0;
                                log.info("这次Get请求为机器读取");
                            }else {
                                log.info("这次Get请求为控制端记录");
                            }
                        } else {
                            initResource.release();
                            return null;
                        }
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } else {
                    return null;
                }
                initResource.release();
                IResource.release();
                return returnList;
            } catch (SocketException | SocketTimeoutException se) {
                try {
                    if (!this.Reconnect()){
                        throw new Exception("尝试重连失败，请重启控制端！");
                    }
                    if (TCPRunner.isInitSuccess) {
                        if (isSet) {
                            returnList = new ArrayList<>();
                            getterMap.put(MethodName.ReadActualCO2, storexMethodService.readActualCO2());
                            getterMap.put(MethodName.ReadActualHumidity, storexMethodService.readActualHumidity());
                            getterMap.put(MethodName.ReadActualN2, storexMethodService.readActualN2());
                            getterMap.put(MethodName.ReadActualO2, storexMethodService.readActualO2());
                            getterMap.put(MethodName.ReadActualTemperature, storexMethodService.readActualTemperature());
                            getterMap.put(MethodName.ReadSetCO2, storexMethodService.readSetCO2());
                            getterMap.put(MethodName.ReadSetHumidity, storexMethodService.readSetHumidity());
                            getterMap.put(MethodName.ReadSetN2, storexMethodService.readSetN2());
                            getterMap.put(MethodName.ReadSetO2, storexMethodService.readSetO2());
                            getterMap.put(MethodName.ReadTemperature, storexMethodService.readTemperature());
                            getterMap.put(MethodName.ReadShakerSpeed, storexMethodService.readShakerSpeed());
                            getterMap.put(MethodName.ReadShakerStatus, storexMethodService.readShakerStatus());
                            getterMap.forEach((key, value) -> {
                                GetReturn getReturn = new GetReturn();
                                getReturn.setGetName(key);
                                getReturn.setGetValue(value);
                                log.info("查询到的get为:"+key+":"+value);
                                returnList.add(getReturn);
                            });
                            isSet = false;
                        }
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } catch (Exception e) {
                    throw new Exception("网络导致socket断开连接，重连失败，请尝试重启客户端");
                }
                initResource.release();
                IResource.release();
                return returnList;
            } catch (Exception e) {
                initResource.release();
                IResource.release();
                throw e;
            }
        }catch (Exception e){
            initResource.release();
            IResource.release();
            throw e;
        }
    }

    public RegisterInfo incubatorInfo() throws IOException {
        RegisterInfo registerInfo=new RegisterInfo();

        BasicInfo basicInfo = new BasicInfo("自动化培养箱","Incubator","storeXIC","LiCONiC","刘荣","1.0.1","培养箱",0,30,1,1,"data:image/jpeg;base64,"+ ImageUtil.imagePngToBase64String("Incubator.jpeg"),1, "2.0");
        /*"data:image/jpeg;base64,"+ ImageUtil.imagePngToBase64String("Incubator.jpeg")*/
        registerInfo.setBasicInfo(basicInfo);

        AdvancedInfo advancedInfo = new AdvancedInfo();

        List<equipmentFunction> equipmentFunctions = new ArrayList<>();
        String functionFormJsonStructure = EquipmentFunctionValue.Incubate.getFunctionFormJsonStructure();
        JSONObject jsonObject = JSON.parseObject(functionFormJsonStructure);
        JSONArray nest = (JSONArray) jsonObject.get("nest");
        for (Object o : nest) {
            JSONObject o1 = (JSONObject) o;
            if (o1.get("key").equals("temperature")){
                JSONObject description = (JSONObject) o1.get("description");
                description.replace("minValue",tem_min);
                description.replace("maxValue",tem_max);
            }
            if (o1.get("key").equals("speed")){
                JSONObject description = (JSONObject) o1.get("description");
                description.replace("maxValue",shake_speed);
            }
        }
        EquipmentFunctionValue.Incubate.setFunctionFormJsonStructure(functionFormJsonStructure);
        equipmentFunctions.add(EquipmentFunctionValue.Incubate);


        List<equipmentOperation> equipmentOperations = new ArrayList<>();
        equipmentOperations.add(EquipmentOperationValue.CloseGate);
        equipmentOperations.add(EquipmentOperationValue.OpenGate);
        equipmentOperations.add(EquipmentOperationValue.CloseCommunication);
        equipmentOperations.add(EquipmentOperationValue.OpenCommunication);
        equipmentOperations.add(EquipmentOperationValue.ResetInit);
        equipmentOperations.add(EquipmentOperationValue.ActivateShaker);
        equipmentOperations.add(EquipmentOperationValue.In);
        equipmentOperations.add(EquipmentOperationValue.Out);

        List<equipmentSetInfo> equipmentSetInfos = new ArrayList<>();
        equipmentSetInfo shakerSpeed = EquipmentSetInfoValue.ShakerSpeed;
        List<String> setValue = shakerSpeed.getSetValue();
        setValue.set(1,shake_speed);

        equipmentSetInfo setTemperature = EquipmentSetInfoValue.SetTemperature;
        List<String> setValue1 = setTemperature.getSetValue();
        setValue1.set(0,tem_min);
        setValue1.set(1,tem_max);

        equipmentSetInfos.add(setTemperature);
        equipmentSetInfos.add(EquipmentSetInfoValue.SetCO2);
        equipmentSetInfos.add(EquipmentSetInfoValue.SetHumidity);
        equipmentSetInfos.add(EquipmentSetInfoValue.SetN2);
        equipmentSetInfos.add(EquipmentSetInfoValue.SetO2);
        equipmentSetInfos.add(shakerSpeed);

        List<equipmentGetInfo> equipmentGetInfos = new ArrayList<>();
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadTemperature);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadActualTemperature);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadActualCO2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadActualHumidity);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadActualN2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadActualO2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadSetCO2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadSetHumidity);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadSetN2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadSetO2);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadShakerSpeed);
        equipmentGetInfos.add(EquipmentGetInfoValue.ReadShakerStatus);



        List<equipmentNest> equipmentNests = new ArrayList<>();
        equipmentNests.add(EquipmentNestValue.P1_1);
        equipmentNests.add(EquipmentNestValue.P1_2);
        equipmentNests.add(EquipmentNestValue.P1_3);
        equipmentNests.add(EquipmentNestValue.P1_4);
        equipmentNests.add(EquipmentNestValue.P1_5);
        equipmentNests.add(EquipmentNestValue.P1_6);
        equipmentNests.add(EquipmentNestValue.P1_7);
        equipmentNests.add(EquipmentNestValue.P1_8);
        equipmentNests.add(EquipmentNestValue.P1_9);
        equipmentNests.add(EquipmentNestValue.P1_10);

        equipmentNests.add(EquipmentNestValue.P2_1);
        equipmentNests.add(EquipmentNestValue.P2_2);
        equipmentNests.add(EquipmentNestValue.P2_3);
        equipmentNests.add(EquipmentNestValue.P2_4);
        equipmentNests.add(EquipmentNestValue.P2_5);
        equipmentNests.add(EquipmentNestValue.P2_6);
        equipmentNests.add(EquipmentNestValue.P2_7);
        equipmentNests.add(EquipmentNestValue.P2_8);
        equipmentNests.add(EquipmentNestValue.P2_9);
        equipmentNests.add(EquipmentNestValue.P2_10);
        equipmentNests.add(EquipmentNestValue.POutSide);


        equipmentEnterAndExit equipmentEnterAndExit = EquipmentEnterAndExitValue.EnterAndExit;

        advancedInfo.setEquipmentOperations(equipmentOperations);
        advancedInfo.setEquipmentFunctions(equipmentFunctions);
        advancedInfo.setEquipmentGetInfos(equipmentGetInfos);
        advancedInfo.setEquipmentSetInfos(equipmentSetInfos);
        advancedInfo.setEquipmentNests(equipmentNests);
        advancedInfo.setEquipmentEnterAndExit(equipmentEnterAndExit);
        registerInfo.setAdvancedInfo(advancedInfo);
        return registerInfo;
    }
    /*@Scheduled*/
    public Finish incubatorEnterAndExit(EnterOrExitData enterOrExitData) throws Exception {
        try {
            try {
                    Map<String, Object> map = (Map<String, Object>) enterOrExitData.getEnterOrExitValue();
                    PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
                    String Nest = plateMethod.getStorexColumn() + "," + plateMethod.getStorexRow();
                    AtomicReference<Boolean> isNestExist = new AtomicReference<>(false);
                    TxtReadline.sample_status.forEach((key, value) -> {
                        if (key.contains(Nest)) {
                            isNestExist.set(true);
                        }
                    });
                    if (isNestExist.get()){
                        log.info("EnterAndExit-准备获取串口资源...");
                        initResource.acquire();
                    if (TCPRunner.isInitSuccess) {
                        IResource.acquire();
                        log.info("EnterAndExit-获取串口资源完成...");
                        log.info("准备解析对象");
                        Consumer<EnterOrExitData> consumer = inAndOutMap.get(enterOrExitData.getEnterOrExitName());
                        if (!Objects.nonNull(consumer)) {
                            throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                        }
                        log.info("准备执行方法");
                        consumer.accept(enterOrExitData);
                        log.info("准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
            }else {
                        throw new Exception("培养箱内无对应该位置信息,出入板功能执行失败！");
                    }
            } catch (SocketException | SocketTimeoutException se) {
                try {
                    if (!this.Reconnect()){
                        throw new Exception("尝试重连失败，请重启控制端！");
                    }
                    if (TCPRunner.isInitSuccess) {
                        byte[] data = HextoByte.hexToByteArray(CommandEnum.STATUS.getInstruction() + COMMAND_SUFFIX);
                        TCPClient.Write(data);
                        //SerialTool.sendToPort(TCPRunner.serialPort, data);
                        Thread.sleep(1000);
                        String read = TCPClient.Read();
                        if (read.contains("1")) {
                            /*throw new Exception("培养箱当前为空闲中，重试无法实现状态捕获！");*/
                            String s = storexService.commonOperation(CommandEnum.ReadTransferStationPlateSensor);
                            if (enterOrExitData.getEnterOrExitName().equals(MethodName.ImportPlate)) {
                                //还没做
                                if (s.contains("1")){
                                    Map<String, Integer> map = (Map<String, Integer>) enterOrExitData.getEnterOrExitValue();
                                    PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
                                    String Nest = plateMethod.getStorexColumn() + "," + plateMethod.getStorexRow();
                                    AtomicReference<Boolean> isNestExist = new AtomicReference<>(false);
                                    TxtReadline.sample_status.forEach((key, value) -> {
                                        if (key.contains(Nest)) {
                                            isNestExist.set(true);
                                        }
                                    });
                                    if (isNestExist.get()){
                                        if (TCPRunner.isInitSuccess) {
                                            log.info("准备解析对象");
                                            Consumer<EnterOrExitData> consumer = inAndOutMap.get(enterOrExitData.getEnterOrExitName());
                                            if (!Objects.nonNull(consumer)) {
                                                throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                                            }
                                            log.info("准备执行方法");
                                            consumer.accept(enterOrExitData);
                                            log.info("准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
                                        } else {
                                            throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                                        }
                                    }else {
                                        throw new Exception("培养箱内无对应该位置信息,出入板功能执行失败！");
                                    }
                                }else if (s.contains("0")){
                                    //做完了
                                    log.info("进板功能已经做完，直接返回");
                                }
                            }else if (enterOrExitData.getEnterOrExitName().equals(MethodName.ExportPlate)){
                                //做完了
                                if (s.contains("1")){
                                    //做完了
                                    log.info("出板功能已经做完，直接返回");
                                }else if (s.contains("0")){
                                    //还没做
                                    Map<String, Integer> map = (Map<String, Integer>) enterOrExitData.getEnterOrExitValue();
                                    PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
                                    String Nest = plateMethod.getStorexColumn() + "," + plateMethod.getStorexRow();
                                    AtomicReference<Boolean> isNestExist = new AtomicReference<>(false);
                                    TxtReadline.sample_status.forEach((key, value) -> {
                                        if (key.contains(Nest)) {
                                            isNestExist.set(true);
                                        }
                                    });
                                    if (isNestExist.get()){
                                        if (TCPRunner.isInitSuccess) {
                                            log.info("准备解析对象");
                                            Consumer<EnterOrExitData> consumer = inAndOutMap.get(enterOrExitData.getEnterOrExitName());
                                            if (!Objects.nonNull(consumer)) {
                                                throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                                            }
                                            log.info("准备执行方法");
                                            consumer.accept(enterOrExitData);
                                            log.info("准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
                                        } else {
                                            throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                                        }
                                    }else {
                                        throw new Exception("培养箱内无对应该位置信息,出入板功能执行失败！");
                                    }
                                }
                            }
                        }else {
                            storexService.whileStatus();
                        }
                        log.info("准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } catch (Exception e) {
                    throw new Exception("网络导致socket断开连接，重连失败，请尝试重启客户端"+e.getMessage());
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                log.info("执行失败，准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
                initResource.release();
                IResource.release();
                Finish finish = new Finish("error", e.getMessage() + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue(), null, null, null);
                return finish;
            }

            Finish finish = new Finish("finish", "", null, null, null);
            return finish;
        }catch (Exception e){
            log.error(e.getMessage());
            log.info("执行失败，准备返回EnterAndExit" + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue());
            initResource.release();
            IResource.release();
            Finish finish = new Finish("error", e.getMessage() + enterOrExitData.getEnterOrExitName() + ":" + enterOrExitData.getEnterOrExitValue(), null, null, null);
            return finish;
        }
    }

    @Async
    public Finish incubatorSet(List<SetData> data) throws Exception {
        try {
            for (SetData s :
                    data) {
                try {
                    log.info("set-准备获取串口资源...");
                    initResource.acquire();
                    if (TCPRunner.isInitSuccess) {
                        IResource.acquire();
                        log.info("set-获取串口资源完成...");
                        log.info("准备解析对象");
                        Consumer<SetData> consumer = setterMap.get(s.getSetName());
                        if (!Objects.nonNull(consumer)) {
                            throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                        }
                        log.info("准备执行方法");
                        consumer.accept(s);
                        log.info("准备发送set回调接口");
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } catch (SocketException | SocketTimeoutException se) {
                    try {
                        if (!this.Reconnect()){
                            throw new Exception("尝试重连失败，请重启控制端！");
                        }
                        if (TCPRunner.isInitSuccess) {
                            log.info("准备解析对象");
                            Consumer<SetData> consumer = setterMap.get(s.getSetName());
                            if (!Objects.nonNull(consumer)) {
                                throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                            }
                            log.info("准备执行方法");
                            consumer.accept(s);
                            log.info("准备发送set回调接口");
                            initResource.release();
                            IResource.release();
                        } else {
                            throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                        }
                    } catch (Exception e) {
                        throw new Exception("网络导致socket断开连接，重连失败，请尝试重启客户端");
                    }

                } catch (Exception e) {
                    log.info("执行set失败，准备发送回调接口");
                    initResource.release();
                    IResource.release();
                    Finish finish = new Finish("error", e.getMessage(), null, null, null);
                    return finish;
                }
            }
            isSet = true;
            Finish finish = new Finish("finish", "", null, null, null);
            return finish;
        }catch (Exception e){
            log.info("执行set失败，准备发送回调接口");
            initResource.release();
            IResource.release();
            Finish finish = new Finish("error", e.getMessage(), null, null, null);
            return finish;
        }
    }

    @Async
    public void incubatorOperation(OperationData data) throws Exception {
        try {
            try {
                log.info("operation-准备获取串口资源...");
                initResource.acquire();
                AtomicReference<Boolean> isNestExist = new AtomicReference<>(false);
                if (data.getOperationName().equals(MethodName.ImportPlate) || data.getOperationName().equals(MethodName.ExportPlate)) {
                    Map<String, Integer> map = (Map<String, Integer>) data.getOperationParam();
                    PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
                    String Nest = plateMethod.getStorexColumn() + "," + plateMethod.getStorexRow();
                    TxtReadline.sample_status.forEach((key, value) -> {
                        if (key.contains(Nest)) {
                            isNestExist.set(true);
                        }
                    });
                if (isNestExist.get()) {
                    if (TCPRunner.isInitSuccess) {
                        IResource.acquire();
                        log.info("operation-获取串口资源完成...");
                        log.info("准备解析对象");
                        Consumer<OperationData> consumer = debugMap.get(data.getOperationName());
                        if (!Objects.nonNull(consumer)) {
                            throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                        }
                        log.info("准备执行方法");
                        consumer.accept(data);
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } else {
                    throw new Exception("培养箱内无对应该位置信息,出入板功能执行失败！");
                }
            }else {
                    if (TCPRunner.isInitSuccess) {
                        IResource.acquire();
                        log.info("准备解析对象");
                        Consumer<OperationData> consumer = debugMap.get(data.getOperationName());
                        if (!Objects.nonNull(consumer)) {
                            throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                        }
                        log.info("准备执行方法");
                        consumer.accept(data);
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                }
            } catch (SocketException | SocketTimeoutException se) {
                try {
                    if (!this.Reconnect()){
                        throw new Exception("尝试重连失败，请重启控制端！");
                    }
                    if (TCPRunner.isInitSuccess) {
                        if (data.getOperationName().equals(MethodName.ImportPlate)||data.getOperationName().equals(MethodName.ExportPlate)){
                            byte[] data1 = HextoByte.hexToByteArray(CommandEnum.STATUS.getInstruction() + COMMAND_SUFFIX);
                            TCPClient.Write(data1);
                            //SerialTool.sendToPort(TCPRunner.serialPort, data);
                            Thread.sleep(1000);
                            String read = TCPClient.Read();
                            if (read.contains("1")) {
                                throw new Exception("培养箱当前为空闲中，重试无法实现状态捕获！");
                            }else {
                                storexService.whileStatus();
                            }
                        } else {
                            log.info("准备解析对象");
                            Consumer<OperationData> consumer = debugMap.get(data.getOperationName());
                            if (!Objects.nonNull(consumer)) {
                                throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                            }
                            log.info("准备执行方法");
                            consumer.accept(data);
                        }
                        initResource.release();
                        IResource.release();
                    } else {
                        throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
                    }
                } catch (Exception e) {
                    throw new Exception("网络导致socket断开连接，重连失败，请尝试重启客户端"+e);
                }
            } catch (Exception e) {
                initResource.release();
                IResource.release();
                log.error("调试功能报错:" + e.getMessage());
                log.info("执行失败，准备发送回调接口");
            }
        }catch (Exception e){
            initResource.release();
            IResource.release();
            log.error("调试功能报错:" + e.getMessage());
            log.info("执行失败，准备发送回调接口");
        }
    }


    @Async
    public void incubatorFunction(FunctionData data) throws ClassNotFoundException {
        try {
            log.info("function-准备获取串口资源...");
            initResource.acquire();
            IResource.acquire();
            log.info("function-获取串口资源完成...");
            if (TCPRunner.isInitSuccess) {
                log.info("准备解析对象,设置温度和转速(需要占用串口)");
                Map<String,Integer> map= (Map<String, Integer>) data.getFunctionParam();
                Incubate incubate = JSON.parseObject(JSON.toJSONString(map), Incubate.class);
                if (incubate.getSet_param().equals("1")){
                    SetData setTemData=new SetData();
                    setTemData.setSetValue(String.valueOf(incubate.getTemperature()));
                    storexMethodService.setTemperature(setTemData);
                    SetData setSpData=new SetData();
                    setSpData.setSetValue(String.valueOf(incubate.getSpeed()));
                    storexMethodService.shakerSpeed(setSpData);
                }else if (incubate.getSet_param().equals("0")){
                    log.info("function-incubate-温度和转速不设置.");
                }else {
                    throw new Exception("function-incubate-errorParam");
                }
                log.info("设置温度和转速(需要占用串口)，完成,释放资源");
                initResource.release();
                IResource.release();
                Consumer<FunctionData> consumer = functionMap.get(data.getFunctionName());
                if (!Objects.nonNull(consumer)) {
                    throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
                }
                log.info("准备执行方法");
                consumer.accept(data);
                Finish finish = new Finish("finish", "", data.getInstructionId(), data.getNestId(), null);
                log.info("准备发送function回调接口" + data.getFunctionName() + ":" + data.getFunctionParam());
                rest.Post(finish);
                log.info("发送回调接口完成");

            }else {
                throw new Exception("初始化设备失败，请尝试重启设备和控制端程序！");
            }
        }catch (Exception e){
            log.info("执行function失败，准备发送回调接口"+data.getFunctionName()+":"+data.getFunctionParam());
            Finish finish = new Finish("error", e.getMessage()+data.getFunctionName()+":"+data.getFunctionParam(),data.getInstructionId(),data.getNestId(),null);
            rest.Post(finish);
            log.info("发送回调接口完成");
            initResource.release();
            IResource.release();
        }
    }

    @Async
    public void incubatorSimulation(FunctionData data) throws ClassNotFoundException {
        try {
            log.info("准备解析对象");
            Consumer<FunctionData> consumer = simulationMap.get(data.getFunctionName());
            if (!Objects.nonNull(consumer)) {
                throw new ServiceException(ServiceExceptionEnum.NO_SUCH_FUNCTION);
            }
            log.info("准备执行方法");
            consumer.accept(data);
            Finish finish = new Finish("finish","",data.getInstructionId(),data.getNestId(),null);
            log.info("准备发送回调接口");
            rest.Post(finish);
            log.info("发送回调接口完成");
        }catch (Exception e){
            log.info("执行失败，准备发送回调接口");
            Finish finish = new Finish("error", e.getMessage(),data.getInstructionId(),data.getNestId(),null);
            rest.Post(finish);
            log.info("发送回调接口完成");
        }
    }
}
