package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentEnterAndExit.java
 * @Description TODO
 * @createTime 2022年04月01日 14:54:00
 */
@Data
@AllArgsConstructor
public class equipmentEnterAndExit {
    //进入方法名称
    private String enterMethodName;
    //进入方法值
    private String enterMethodValue;
    //进入方法描述
    private String enterMethodDescription;
    //退出方法名称
    private String exitMethodName;
    //退出方法值
    private String exitMethodValue;
    //退出方法描述
    private String exitMethodDescription;
}
