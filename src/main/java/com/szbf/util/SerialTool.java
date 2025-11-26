package com.szbf.util;

import com.szbf.common.ServiceException;

import com.szbf.constant.ServiceExceptionEnum;


import gnu.io.*;
import lombok.extern.slf4j.Slf4j;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

@Slf4j
public class SerialTool {
    public static SerialTool serialTool = null;

    static {
        //在该类被ClassLoader加载时就初始化一个SerialTool对象
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
    }
    //私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
    private SerialTool() {}


    public static SerialTool getSerialTool() {
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
        return serialTool;
    }

    public static final ArrayList<String> findPort() {

        //获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();

        ArrayList<String> portNameList = new ArrayList<>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        log.info("可用串口：{}" + portNameList);
        return portNameList;

    }


    public static final SerialPort openPort(String portName) throws Exception {

        try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {

                SerialPort serialPort = (SerialPort) commPort;

                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
                } catch (UnsupportedCommOperationException e) {
                    throw new ServiceException(ServiceExceptionEnum.SERIAL_PARAMETER_FAILURE);
                }

                System.out.println("Open " + portName + " sucessfully !");
                return serialPort;

            }
            else {
                //不是串口
                throw new ServiceException(ServiceExceptionEnum.NOT_SERIAL_PORT);
            }
        } catch (NoSuchPortException e1) {
            throw new ServiceException(ServiceExceptionEnum.NO_SUCH_PORT);
        } catch (PortInUseException e2) {
            throw new ServiceException(ServiceExceptionEnum.PORT_IN_USE);
        }
    }

    /**
     * 关闭串口
     * @param serialPort 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }


    public static void sendToPort(SerialPort serialPort, byte[] order) throws Exception {

        OutputStream out = null;

        try {

            out = serialPort.getOutputStream();
            out.write(order);
            out.close();

            System.out.println("发送成功");

        } catch (IOException e) {
            throw new ServiceException(ServiceExceptionEnum.SEND_SERIAL_DATA_FAILURE);
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                throw new ServiceException(ServiceExceptionEnum.CLOSE_OUTPUT_STREAM_FAILURE);
            }
        }

    }

    public static byte[] readFromPort(SerialPort serialPort) throws Exception {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            int bufflenth = in.available();        //获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth];    //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            throw new ServiceException(ServiceExceptionEnum.READ_SERIAL_DATA_FAILURE);
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
                throw new ServiceException(ServiceExceptionEnum.CLOSE_INPUT_STREAM_FAILURE);
            }

        }

        return bytes;

    }


    public static Result addListener(SerialPort port, SerialPortEventListener listener)  {
        try {
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);
            return Result.success();
        } catch (TooManyListenersException e) {
            log.error("SerialTool addListener() 添加串口监听异常：{}",e);
            return Result.failed("串口监听添加失败！: "+e.getMessage());
        }
    }

}
