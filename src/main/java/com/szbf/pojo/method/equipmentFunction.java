package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentFunction.java
 * @Description TODO
 * @createTime 2022年04月01日 14:55:00
 */
@Data
@AllArgsConstructor
public class equipmentFunction {
    //方法名 如:wash,shaker
    private String functionName;
    //功能表单简介中文名 如测量，震动
    private String functionTitleCN;
    //功能表单简介英文名
    private String functionTitleEN;
    //设备描述
    private String functionDescription;
    //功能执行系统缺省时间 单位s
    private String functionDefaultPeriod;
    //功能表单分类 中文名
    private String functionCategoryCN;
    //功能表单分类 英文名
    private String functionCategoryEN;
    //图像黑Base64编码(png)
    private String iconBlack;
    //图像白Base64编码(png)
    private String iconWhite;
    //动态表单json结构 其中包含参数
    private String functionFormJsonStructure;
}
