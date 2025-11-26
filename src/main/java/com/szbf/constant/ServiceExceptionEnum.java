package com.szbf.constant;

import com.szbf.common.BaseErrorInfoInterface;

public enum  ServiceExceptionEnum implements BaseErrorInfoInterface {

    NO_SUCH_FUNCTION(20101,"没有处理的该命令的方法！"),
    NO_SUCH_PORT(20102,"没有该串口设备！"),
    NOT_SERIAL_PORT(20103,"不是串口类型！"),
    PORT_IN_USE(20104,"串口已被占用！"),
    READ_SERIAL_DATA_FAILURE(20105,"串口读取数据时出错！"),
    SEND_SERIAL_DATA_FAILURE(20106,"向串口发送数据失败！"),
    CLOSE_INPUT_STREAM_FAILURE(20107,"关闭串口对象输入流出错！"),
    CLOSE_OUTPUT_STREAM_FAILURE(20108,"关闭串口对象的输出流出错！"),
    SERIAL_PARAMETER_FAILURE(20109,"设置串口参数失败，打开串口操作未完成！");
    private Integer code;

    private String message;

    ServiceExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
