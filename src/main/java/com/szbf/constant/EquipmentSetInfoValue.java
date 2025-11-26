package com.szbf.constant;

import com.szbf.pojo.method.equipmentSetInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName EquipmentSetInfoValue.java
 * @Description TODO
 * @createTime 2022年04月14日 15:57:00
 */
@Service
@Component
@Slf4j
public class EquipmentSetInfoValue {

    public static final equipmentSetInfo ShakerSpeed = new equipmentSetInfo("ShakerSpeed","震动速度设置","ShakerSpeed Setting","float",new ArrayList<String>(){{add("1");add("40");add("0");}},"1RPM","震动速度");
    public static final equipmentSetInfo SetO2 = new equipmentSetInfo("SetO2","氧气设置","O2 Setting","float",new ArrayList<String>(){{add("1");add("100");add("2");}},"%","设置氧气浓度");
    public static final equipmentSetInfo SetCO2 = new equipmentSetInfo("SetCO2","二氧化碳设置","CO2 Setting","float",new ArrayList<String>(){{add("1");add("100");add("2");}},"%","设置二氧化碳浓度");
    public static final equipmentSetInfo SetHumidity = new equipmentSetInfo("SetHumidity","湿度设置","Humidity Setting","float",new ArrayList<String>(){{add("1");add("100");add("1");}},"%","设置湿度");
    public static final equipmentSetInfo SetN2 = new equipmentSetInfo("SetN2","氮气设置","N2 Setting","float",new ArrayList<String>(){{add("1");add("100");add("2");}},"%","设置氮气浓度");
    public static final equipmentSetInfo SetTemperature = new equipmentSetInfo("SetTemperature","温度设置","Temperature Setting","float",new ArrayList<String>(){{add("1");add("45");add("1");}},"℃","设置摄氏度");
}
