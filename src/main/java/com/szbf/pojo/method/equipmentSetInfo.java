package com.szbf.pojo.method;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName equipmentSetInfo.java
 * @Description TODO
 * @createTime 2022年04月01日 14:55:00
 */
@Data
@AllArgsConstructor
public class equipmentSetInfo {
    //属性名
    private String setName;
    //属性标题 中文名
    private String setTitleCN;
    //属性标题 英文名
    private String setTitleEN;
    //属性输入框参数如 input select
    private String setType;
    //输入选择范围 如input 0-60 填[0,60],如float其中0-60 填[0,60,0.1],0.1为取值单位 如select "low","medium","high","off"则为["low","medium","high","off"]  
    private List<String> setValue;
    //属性单位 如%,mm,cm
    private String setUnit;
    //属性描述
    private String setDescription;
}
