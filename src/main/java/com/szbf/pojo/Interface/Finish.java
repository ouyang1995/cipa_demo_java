package com.szbf.pojo.Interface;

import com.szbf.pojo.result_output;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName Finish.java
 * @Description TODO
 * @createTime 2022年02月17日 14:57:00
 */
@Data
@AllArgsConstructor
public class Finish {
    //完成状态，finish,error
    private String completion;
    //错误信息，打印异常信息，若completion为 error 则不为null
    private String errorMsg;
    //指令id
    private String instructionId;
    //位置id
    private String nestId;
    //用于拥有测量数据的设备，可为null
    private List<result_output> resultOutput;
}
