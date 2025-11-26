package com.szbf.pojo.method;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName RegisterInfo.java
 * @Description TODO
 * @createTime 2022年04月01日 14:48:00
 */
@Data
public class RegisterInfo {
    //基础数据
    private BasicInfo basicInfo;
    //高级数据
    private AdvancedInfo advancedInfo;
}
