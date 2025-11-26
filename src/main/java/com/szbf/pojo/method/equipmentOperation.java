package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentOperation.java
 * @Description TODO
 * @createTime 2022年04月01日 14:54:00
 */
@Data
@AllArgsConstructor
public class equipmentOperation {
    //调试方法名 如：wash
    private String operationName;
    //调试名称 中文名
    private String operationTitleCN;
    //调试名称 英文名
    private String operationTitleEN;
    //调试描述
    private String operationDescription;
    //调试动态表单json 其中包含参数
    private String operationFormJsonStructure;
}
