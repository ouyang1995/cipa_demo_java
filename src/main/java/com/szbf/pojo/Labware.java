package com.szbf.pojo;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName Labware.java
 * @Description TODO
 * @createTime 2022年04月07日 10:17:00
 */

@Data
//物料信息，一般根据需求接收即可，具体内容看物料对象结构定义
public class Labware {
    private String LabwareName;
    private String capacity;
    private int capacityRow;
    private int capacityColumn;
}
