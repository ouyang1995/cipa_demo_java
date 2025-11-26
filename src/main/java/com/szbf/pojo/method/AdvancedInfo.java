package com.szbf.pojo.method;

import lombok.Data;

import java.util.List;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName AdvancedInfo.java
 * @Description TODO
 * @createTime 2022年04月01日 14:49:00
 */
@Data
public class AdvancedInfo {
    //储存关于功能表单方法的信息
    private List<equipmentFunction> equipmentFunctions;
    //储存关于设备状态的信息
    private List<equipmentGetInfo> equipmentGetInfos;
    //存储关于设备属性的信息
    private List<equipmentSetInfo> equipmentSetInfos;
    //存储关于设备位置的信息
    private List<equipmentNest> equipmentNests;
    //存储关于设备调试的信息
    private List<equipmentOperation> equipmentOperations;
    //存储关于设备出入方法的信息
    private equipmentEnterAndExit equipmentEnterAndExit;
}
