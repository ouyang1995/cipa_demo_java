package com.szbf.pojo;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName result_output.java
 * @Description TODO
 * @createTime 2022年04月07日 10:28:00
 */
@Data
public class result_output {
    //测量数据
    private ResultData resultData;
    //在function_param中的string result_output
    private String name;
}
