package com.szbf.pojo.Interface;

import com.szbf.pojo.Labware;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName FunctionData.java
 * @Description TODO
 * @createTime 2022年02月17日 15:06:00
 */
@Data
public class FunctionData {
    //方法名 如:wash,shaker
    private String functionName;
    //指令id
    private String instructionId;
    //物料对象，里面取自己需要的字段即可
    private Labware labwareInfo;
    //设备名称，可为null
    private String equipmentName;
    //位置id
    private String nestId;
    //userId 可为null，一般用于拥有测量数据的设备
    private String userId;
    //taskId 可为null，一般用于拥有测量数据的设备
    private String taskId;
    //设备功能参数
    public Object functionParam;
}

