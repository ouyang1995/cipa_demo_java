package com.szbf.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.szbf.constant.CommandEnum;
import com.szbf.constant.MethodName;
import com.szbf.pojo.*;
import com.szbf.pojo.Interface.EnterOrExitData;
import com.szbf.pojo.Interface.FunctionData;
import com.szbf.pojo.Interface.OperationData;
import com.szbf.pojo.Interface.SetData;
import com.szbf.service.*;
import com.szbf.util.GetUtil;
import com.szbf.util.Rest;
import com.szbf.util.TxtReadline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Component
@Slf4j
public class IncubatorServiceImplement implements StorexMethodService{
    @Autowired
    StorexService storexService;
    @Autowired
    Rest rest;
    @Value("${cfg.shakermanagement}")
    private String shakermanagement;

    @Override
    public void openCommunication(FunctionData functionData) throws Exception{
            Thread.sleep(500);
            //操作命令
            storexService.commonOperation(CommandEnum.OPEN_COMMUNICATION);
    }


    public void closeCommunication(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.CLOSE_COMMUNICATION);
    }


    public void importPlate(FunctionData functionData) throws Exception{
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) functionData.getFunctionParam();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);

        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET2);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT2A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT2B);
        }
        storexService.importPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"1");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb,Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");

        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    public void exportPlate(FunctionData functionData) throws Exception{
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) functionData.getFunctionParam();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET2);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT2A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT2B);
        }
        storexService.exportPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"0");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb, Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");

        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    @Override
    public void incubateDebug(OperationData operationData) throws Exception {
        Map<String,Integer> map= (Map<String, Integer>) operationData.getOperationParam();
        Incubate incubate = JSON.parseObject(JSON.toJSONString(map), Incubate.class);
        Thread.sleep(incubate.getTraining_time()*1000);
    }

    @Override
    public void openCommunicationDebug(OperationData operationData) throws Exception {
        Thread.sleep(500);
        //操作命令
        storexService.commonOperation(CommandEnum.OPEN_COMMUNICATION);
    }

    @Override
    public void closeCommunicationDebug(OperationData operationData) throws Exception {
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.CLOSE_COMMUNICATION);
    }

    @Override
    public void importPlateDebug(OperationData operationData) throws Exception {
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) operationData.getOperationParam();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        storexService.importPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"1");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb,Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");

        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    @Override
    public void exportPlateDebug(OperationData operationData) throws Exception {
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) operationData.getOperationParam();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        storexService.exportPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"0");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb,Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");

        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    @Override
    public void openGateDebug(OperationData operationData) throws Exception {
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.OPEN_GATE);
        storexService.whileStatus();
    }

    @Override
    public void closeGateDebug(OperationData operationData) throws Exception {
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.CLOSE_GATE);
    }

    @Override
    public void resetInitDebug(OperationData operationData) throws Exception {
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.RESET);
        Thread.sleep(300);
        storexService.commonOperation(CommandEnum.SOFT_RESET);
        Thread.sleep(300);
        storexService.commonOperation(CommandEnum.INITIALIZE);
        Thread.sleep(300);
    }

    public void incubate(FunctionData functionData) throws Exception{
        Map<String,Integer> map= (Map<String, Integer>) functionData.getFunctionParam();
        Incubate incubate = JSON.parseObject(JSON.toJSONString(map), Incubate.class);
        Thread.sleep(incubate.getTraining_time()*1000);
    }

    public void openGate(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.OPEN_GATE);
        storexService.whileStatus();
    }

    public void activateShaker(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        IncubatorHandler.isSet=true;
        storexService.commonOperation(CommandEnum.ACTIVATE_SHAKER);
        storexService.whileStatus();
    }

    public void closeGate(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.CLOSE_GATE);
    }

    public void stopShaker(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.STOP_SHAKER);
        int i=0;
        while (true){
            String status=storexService.commonOperation(CommandEnum.READ_SHAKER_STATUS);
            if (status.contains("0")){
                log.info("shaker disable");
                break;
            }else {
                log.info("waiting to stop shaker");
                Thread.sleep(1000);
                i++;
            }
            if (i==50){
                throw new Exception("shaker error");
            }


        }
    }

    public void shakerSpeed(SetData setData) throws Exception{
      //  Map<String,String> map= (Map<String, String>) setData;
      //  ShakerSpeed shakerSpeed = JSON.parseObject(JSON.toJSONString(map), ShakerSpeed.class);
        Thread.sleep(500);
        String setValue = setData.getSetValue();
        int setValueInt=Integer.parseInt(setValue)/10;
        storexService.commonOperation(String.valueOf(setValueInt),CommandEnum.SET_SHAKER_SPEED);
        Thread.sleep(300);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SHAKER_SPEED).contains(String.valueOf(setValueInt))){
                break;
            }
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

        Thread.sleep(2000);
        stopShaker(null);
        Thread.sleep(2000);
        activateShaker(null);
    }

    public String readShakerStatus() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SHAKER_STATUS));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SHAKER_STATUS),MethodName.ReadShakerStatus);
    }

    public String readShakerSpeed() throws Exception{
        Thread.sleep(500);
        log.info(storexService.commonOperation(CommandEnum.READ_SHAKER_SPEED));
        String stringWithLeadingZeros = storexService.commonOperation(CommandEnum.READ_SHAKER_SPEED);
        String trim = stringWithLeadingZeros.trim();
        String withoutLeadingZeros = trim.replaceFirst("^0+(?!$)", "");
        int i = Integer.parseInt(withoutLeadingZeros)*10;
        return String.valueOf(i);
    }

    public void resetInit(FunctionData functionData) throws Exception{
        Thread.sleep(500);
        storexService.commonOperation(CommandEnum.RESET);
        Thread.sleep(300);
        storexService.commonOperation(CommandEnum.SOFT_RESET);
        Thread.sleep(300);
        storexService.commonOperation(CommandEnum.INITIALIZE);
        Thread.sleep(300);
    }

    public void setTemperature(SetData setData) throws Exception{
      //  Map<String,String> map= (Map<String, String>) setData;
     //   Temperature temperature=  JSON.parseObject(JSON.toJSONString(map), Temperature.class);
        Thread.sleep(500);
        String string_temperature=String.valueOf(Double.parseDouble(setData.getSetValue())*10);
        String substring = string_temperature.substring(0, string_temperature.length() - 2);
        storexService.commonOperation(substring,CommandEnum.SET_TEMPERATURE);
        Thread.sleep(500);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SET_TEMPERATURE).contains(substring)){
                break;
            }
            else {
                log.info("正在验证速度是否正确...");
                Thread.sleep(1000);
            }
            if (i==20){
                log.info("验证速度超时");
                throw new Exception("验证速度超时...");
            }
            i++;
        }
    }

    public String readTemperature() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SET_TEMPERATURE));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SET_TEMPERATURE),MethodName.ReadTemperature);
    }

    public String readActualTemperature() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_ACTUAL_TEMPERATURE));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_ACTUAL_TEMPERATURE),MethodName.ReadActualTemperature);
    }

    public void setHumidity(SetData setData) throws Exception{
     //   Map<String,String> map= (Map<String,String>) setData;
       // Humidity humidity=  JSON.parseObject(JSON.toJSONString(map), Humidity.class);
        Thread.sleep(500);
        String string_temperature=String.valueOf(Double.parseDouble(setData.getSetValue())*10);
        String substring = string_temperature.substring(0, string_temperature.length() - 2);

        Thread.sleep(500);
        storexService.commonOperation(substring,CommandEnum.SET_HUMIDITY);
        Thread.sleep(500);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SET_HUMIDITY).contains(substring)){
                break;
            }
            else {
                System.out.println("正在验证");
                Thread.sleep(1000);
            }
            if (i==5){
                System.out.println("验证超时");
                break;
            }
            i++;
        }
    }

    public String readSetHumidity() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SET_HUMIDITY));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SET_HUMIDITY),MethodName.ReadSetHumidity);
    }

    public String readActualHumidity() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_ACTUAL_HUMIDITY));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_ACTUAL_HUMIDITY),MethodName.ReadActualHumidity);
    }

    public void setCO2(SetData setData) throws Exception{
      //  Map<String,String> map= (Map<String,String>)setData;
    //    CO2 co2=  JSON.parseObject(JSON.toJSONString(map), CO2.class);
        Thread.sleep(500);
        String string_temperature=String.valueOf(Double.parseDouble(setData.getSetValue())*100);
        String substring = string_temperature.substring(0, string_temperature.length() - 2);

        Thread.sleep(500);

        storexService.commonOperation(substring,CommandEnum.SET_CO2);
        Thread.sleep(500);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SET_CO2).contains(substring)){
                break;
            }
            else {
                System.out.println("正在验证");
                Thread.sleep(1000);
            }
            if (i==5){
                System.out.println("验证超时");
                break;
            }
            i++;
        }
    }

    public String readSetCO2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SET_CO2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SET_CO2),MethodName.ReadSetCO2);
    }

    public String readActualCO2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_ACTUAL_CO2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_ACTUAL_CO2),MethodName.ReadActualCO2);
    }

    public void set02(SetData setData) throws Exception{
     //   Map<String,String> map= (Map<String, String>) setData;
     //   O2 o2=  JSON.parseObject(JSON.toJSONString(map), O2.class);
        Thread.sleep(500);
        String string_temperature=String.valueOf(Double.parseDouble(setData.getSetValue())*100);
        String substring = string_temperature.substring(0, string_temperature.length() - 2);

        Thread.sleep(500);
        storexService.commonOperation(substring,CommandEnum.SET_O2);
        Thread.sleep(500);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SET_O2).contains(substring)){
                break;
            }
            else {
                System.out.println("正在验证");
                Thread.sleep(1000);
            }
            if (i==5){
                System.out.println("验证超时");
                break;
            }
            i++;
        }

    }

    public String readSetO2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SET_O2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SET_O2), MethodName.ReadSetO2);
    }


    public String readActualO2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_ACTUAL_O2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_ACTUAL_O2),MethodName.ReadActualO2);
    }

    public void setN2(SetData setData) throws Exception{
     //   Map<String,String> map= (Map<String, String>)setData;
    //    N2 n2=  JSON.parseObject(JSON.toJSONString(map), N2.class);
        Thread.sleep(500);
        String string_temperature=String.valueOf(Double.parseDouble(setData.getSetValue())*100);
        String substring = string_temperature.substring(0, string_temperature.length() - 2);
        Thread.sleep(500);
        storexService.commonOperation(substring,CommandEnum.SET_N2);
        Thread.sleep(500);
        int i=0;
        while (true){
            if (storexService.commonOperation(CommandEnum.READ_SET_N2).contains(substring)){
                break;
            }
            else {
                System.out.println("正在验证");
                Thread.sleep(1000);
            }
            if (i==5){
                System.out.println("验证超时");
                break;
            }
            i++;
        }
    }

    public String readSetN2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_SET_N2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_SET_N2),MethodName.ReadSetN2);
    }

    public String readActualN2() throws Exception{
        Thread.sleep(500);
        System.out.println(storexService.commonOperation(CommandEnum.READ_ACTUAL_N2));
        return GetUtil.GetValueToGetReturnValue(storexService.commonOperation(CommandEnum.READ_ACTUAL_N2),MethodName.ReadActualN2);
    }

    @Override
    public void isShaker(OperationData operationData) throws Exception {
        Map<String,String> map= (Map<String, String>) operationData.getOperationParam();
        IsShaker isShaker=  JSON.parseObject(JSON.toJSONString(map), IsShaker.class);
        Thread.sleep(500);
        if (isShaker.getShaker().equals("true")){
            activateShaker(null);
        }
        if (isShaker.getShaker().equals("false")){
            stopShaker(null);
        }
    }

    @Override
    public void importPlateInAndOut(EnterOrExitData enterOrExitData) throws Exception {
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) enterOrExitData.getEnterOrExitValue();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        storexService.importPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"1");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb,Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");
        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    @Override
    public void exportPlateInAndOut(EnterOrExitData enterOrExitData) throws Exception {
        stopShaker(null);
        Map<String,Integer> map= (Map<String, Integer>) enterOrExitData.getEnterOrExitValue();
        PlateMethod plateMethod = JSON.parseObject(JSON.toJSONString(map), PlateMethod.class);
        if(plateMethod.getStorexColumn()==1){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        if(plateMethod.getStorexColumn()==2){
            storexService.commonOperation(CommandEnum.Plate_SET1);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1A);
            Thread.sleep(300);
            storexService.commonOperation(CommandEnum.Plate_INIT1B);
        }
        storexService.exportPlate(String.valueOf(plateMethod.getStorexColumn()),String.valueOf(plateMethod.getStorexRow()));
        Thread.sleep(1000);
        storexService.whileStatus();
        Thread.sleep(500);
        TxtReadline.sample_status.replace("Sample"+String.valueOf(plateMethod.getStorexColumn())+","+String.valueOf(plateMethod.getStorexRow()),"0");
        List<String> strings=new ArrayList<>();
        TxtReadline.sample_status.forEach((key,value)->{
            strings.add(key+"="+value);
        });
        String Sb=new String();
        for (String s:
                strings) {
            Sb=Sb+s+"\n";
        }
        TxtReadline.writetxtfile(Sb,Paths.get(System.getProperty("user.dir")).getParent() + "\\config\\sample_status.cfg");

        if (shakermanagement.contains("true")){
            storexService.ShakerTrue();
        }
        if (shakermanagement.contains("auto")){
            storexService.AutoShaker();
        }
    }

    @Override
    public void simulaitionIncubate(FunctionData functionData) throws InterruptedException {
      //  Thread.sleep(functionData.getEstimatedTime()*1000);
    }

    @Override
    public void simulaitionOpenCommunication(FunctionData functionData) throws InterruptedException {

     // Thread.sleep(functionData.getEstimatedTime()*1000);
    }

    @Override
    public void simulaitionCloseCommunication(FunctionData functionData) throws InterruptedException {

       // Thread.sleep(functionData.getEstimatedTime()*1000);
    }

    @Override
    public void simulaitionImportPlate(FunctionData functionData) throws InterruptedException {

       // Thread.sleep(functionData.getEstimatedTime()*1000);
    }

    @Override
    public void simulaitionExportPlate(FunctionData functionData) throws InterruptedException {

      //  Thread.sleep(functionData.getEstimatedTime()*1000);
    }

}
