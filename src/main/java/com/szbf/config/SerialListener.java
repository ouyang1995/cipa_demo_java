package com.szbf.config;

import com.szbf.util.SerialTool;


import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class SerialListener implements SerialPortEventListener {

    public static String dataPort = new String();
    public String dataOriginal;

    public String getDataPort() {
        return dataPort;
    }

    public String getDataOriginal() {
        return dataOriginal;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {

            case SerialPortEvent.BI: // 10 通讯中断
                log.error("与串口设备通讯中断,错误");
                //JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
                break;

            case SerialPortEvent.OE: // 7 溢位（溢出）错误

            case SerialPortEvent.FE: // 9 帧错误

            case SerialPortEvent.PE: // 8 奇偶校验错误

            case SerialPortEvent.CD: // 6 载波检测

            case SerialPortEvent.CTS: // 3 清除待发送数据

            case SerialPortEvent.DSR: // 4 待发送数据准备好了

            case SerialPortEvent.RI: // 5 振铃指示

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                break;

            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据

                //System.out.println("found data");
                byte[] data = null;

                try {
                    if (TCPRunner.serialPort == null) {
                        //JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
                        log.error("串口对象为空！监听失败！, 错误");
                    }
                    else {
                        data = SerialTool.readFromPort(TCPRunner.serialPort);    //读取数据，存入字节数组
                        //System.out.println(new String(data));
                        // 自定义解析过程，你在实际使用过程中可以按照自己的需求在接收到数据后对数据进行解析
                            if (data == null || data.length < 1) {    //检查数据是否读取正确
                            //JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
                            log.error("读取数据过程中未获取到有效数据！请检查设备或程序！, 错误");
                            System.exit(0);
                        }
                        else {
                            dataOriginal = new String(data);   //将字节数组数据转换位为保存了原始数据的字符串
                            dataPort+=dataOriginal;
                        }
                    }

                } catch (Exception e) {
                    log.error("读取错误:{}",e);
                    //JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);    //发生读取错误时显示错误信息后退出系统
                }
                break;
        }
    }
}
