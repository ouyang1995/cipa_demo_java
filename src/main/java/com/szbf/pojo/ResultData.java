package com.szbf.pojo;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName ResultData.java
 * @Description TODO
 * @createTime 2022年04月07日 10:29:00
 */
@Data
public class ResultData {
    //在function_param中的样品名称
    private String sample_name;
    //任务id
    private String task_id;
    //长度为2的数组 [8,12] 或者[16,24] 对应板大小
    private int[] size;
    //用户id
    private String user_id;
    //方法名，一般格式为 方法名,参数：参数数值,参数：参数数值 这样来实现
    private String method;
    //设备名称
    private String equipment_name;
    //开始时间
    private String start_time;
    //结束时间
    private String end_time;
    //温度
    private double temperature;
    //测量范围 大小8x12 或者16x24 若需要测量则为1 不需要则为0
    private List<List<Integer>> mask;
    //测量数据 大小对应mask
    private double[][] value;
    //偏移量大小对应mask
    private double[][] stdev;
}
