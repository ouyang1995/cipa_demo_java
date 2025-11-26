package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentGetInfo.java
 * @Description TODO
 * @createTime 2022年04月01日 14:55:00
 */
@Data
@AllArgsConstructor
public class equipmentGetInfo {
    //设备状态名
    private String getName;
    //状态标题 中文名
    private String getTitleCN;
    //状态标题 英文名
    private String getTitleEN;
    //状态类型 如boolean，string
    private String getType;
    //状态单位如 %,mm,cm等，可为null
    private String getUnit;
    //状态描述
    private String getDescription;
}
