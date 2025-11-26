package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName BasicInfo.java
 * @Description TODO
 * @createTime 2022年04月01日 14:48:00
 */
@Data
@AllArgsConstructor
public class BasicInfo {
    //设备名 如洗板机
    private String equipmentName;
    //设备名英文
    private String equipmentNameEN;
    //设备型号 如Spark
    private String equipmentModel;
    //设备制造商
    private String equipmentManufacturer;
    //设备控制端作者
    private String author;
    //控制端版本如v1.0
    private String version;
    //设备类
    private String equipmentClass;
    //是否可急停
    private int canE_Stop;
    //设备功能资源数
    private int functionalResources;
    //做功能是能否移动（兼容性）
    private int runtimeAccessibility;
    //该设备是否能同时多个功能（共用性）
    private int parallelizability;
    //设备图像base64编码
    private String equipmentIcon;
    //设备类型
    private int equipmentType;

    private String protocolVersion;
}
