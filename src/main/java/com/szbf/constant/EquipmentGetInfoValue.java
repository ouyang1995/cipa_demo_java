package com.szbf.constant;

import com.szbf.pojo.method.equipmentGetInfo;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName EquipmentGetInfoValue.java
 * @Description TODO
 * @createTime 2022年04月14日 16:39:00
 */
public class EquipmentGetInfoValue {
    public static final equipmentGetInfo ReadActualCO2 = new equipmentGetInfo("ReadActualCO2","实际CO2","Actual CO2","String","%","读取实际二氧化碳浓度");
    public static final equipmentGetInfo ReadActualHumidity = new equipmentGetInfo("ReadActualHumidity","实际湿度","Actual Humidity","String","%","读取实际湿度");
    public static final equipmentGetInfo ReadActualN2 = new equipmentGetInfo("ReadActualN2","实际氮气","Actual N2","String","%","读取实际氮气浓度");
    public static final equipmentGetInfo ReadActualO2 = new equipmentGetInfo("ReadActualO2","实际氧气","Actual O2","String","%","读取实际氧气浓度");
    public static final equipmentGetInfo ReadActualTemperature = new equipmentGetInfo("ReadActualTemperature","实际温度","Actual Temperature","String","℃","读取实际温度");
    public static final equipmentGetInfo ReadSetCO2 = new equipmentGetInfo("ReadSetCO2","二氧化碳设置","CO2 Setting","String","%","读取二氧化碳浓度");
    public static final equipmentGetInfo ReadSetHumidity = new equipmentGetInfo("ReadSetHumidity","湿度设置","Humidity Setting","String","%","读取湿度");
    public static final equipmentGetInfo ReadSetN2 = new equipmentGetInfo("ReadSetN2","氮气设置","N2 Setting","String","%","读取氮气浓度");
    public static final equipmentGetInfo ReadSetO2 = new equipmentGetInfo("ReadSetO2","氧气设置","O2 Setting","String","%","读取氧气浓度");
    public static final equipmentGetInfo ReadTemperature = new equipmentGetInfo("ReadTemperature","温度设置","Temperature Setting","String","℃","读取温度");
    public static final equipmentGetInfo ReadShakerSpeed = new equipmentGetInfo("ReadShakerSpeed","震动速度设置","ShakerSpeed Setting","String","RPM","读取震动速度");
    public static final equipmentGetInfo ReadShakerStatus = new equipmentGetInfo("ReadShakerStatus","震动状态","ShakerStatus","String","boolean","查询震动状态");

}
