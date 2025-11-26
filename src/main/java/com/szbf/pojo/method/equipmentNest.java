package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentNest.java
 * @Description TODO
 * @createTime 2022年04月01日 14:54:00
 */
@Data
@AllArgsConstructor
public class equipmentNest {
    //位置名
    private String nestName;
    //支持的物料类型 如MP96,MP384,DW96,DW0
    private String labwareType;
    //可使用姿态（0、90、180、270，逗号相隔）
    private String nestPostures;
    //物料进去后是否调用设备Json结构
    private String postEnterFormJsonStructure;
    //物料进入前是否调用设备Json结构
    private String preEnterFormJsonStructure;
    //物料出来后是否调用设备Json结构
    private String postExitFormJsonStructure;
    //物料出来前是否调用设备Json结构
    private String preExitFormJsonStructure;
    //是否对外开放（0代表设备独自管理的位置、1代表设备与机械臂共同管理的位置）
    private int nestAccessibility;
    //位置描述
    private String nestDescription;
    //高度限制
    private float nestHeight;
    //坐标（例如：A-1）
    private String nestCoordinate;
    //列序号
    private int nestColumnOrder;
    //列坐标
    private Integer nestColumnCo;
    //层坐标
    private Integer nestLayerCo;
    //限定物料类型:1-是;0-否
    private int typeOnly;
    // 位置是否是最终目的地 0,1
    private int nestIsDestination;
    // 用于移动到该位置的过渡位置
    private String transitionNest;
}
