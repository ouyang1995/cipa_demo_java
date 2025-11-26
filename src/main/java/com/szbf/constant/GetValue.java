package com.szbf.constant;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName GetValue.java
 * @Description TODO
 * @createTime 2022年10月24日 15:21:00
 */
public class GetValue {
    //第一种需要处理的Get
    public static String[] GetHandler1=new String[]{MethodName.ReadTemperature,MethodName.ReadActualTemperature,MethodName.ReadActualHumidity,MethodName.ReadSetHumidity};
    //第二种需要处理的Get
    public static String[] GetHandler2=new String[]{MethodName.ReadSetCO2,MethodName.ReadActualCO2,MethodName.ReadActualO2,MethodName.ReadSetO2,MethodName.ReadActualN2,MethodName.ReadSetN2};
    //第三种需要处理的Get
    public static String[] GetHandler3=new String[]{MethodName.ReadShakerSpeed};
    //第四种需要处理的Get
    public static String[] GetHandler4=new String[]{MethodName.ReadShakerStatus};
}
