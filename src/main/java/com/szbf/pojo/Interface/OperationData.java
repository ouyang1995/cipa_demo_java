package com.szbf.pojo.Interface;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName OperationData.java
 * @Description TODO
 * @createTime 2022年04月02日 17:38:00
 */
//debug接收对象
@Data
public class OperationData {
    //调试方法名
    private String operationName;
    //调试参数对象
    private Object operationParam;
}
