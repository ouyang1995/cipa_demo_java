package com.szbf.pojo;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName Incubate.java
 * @Description TODO
 * @createTime 2022年02月21日 17:17:00
 */
@Data
public class Incubate {
    private int training_time;
    private float temperature;
    private int speed;
    private String set_param;
}
