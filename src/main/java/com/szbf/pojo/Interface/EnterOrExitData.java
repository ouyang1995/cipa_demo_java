package com.szbf.pojo.Interface;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName EnterOrExitData.java
 * @Description TODO
 * @createTime 2022年04月02日 16:15:00
 */
@Data
@AllArgsConstructor
public class EnterOrExitData {
    //name为 in,out用于区分进出
    private String EnterOrExitName;
    //可为null 若需要则填入
    private Object EnterOrExitValue;
}
