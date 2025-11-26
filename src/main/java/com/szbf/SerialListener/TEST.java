package com.szbf.SerialListener;

import com.szbf.util.HextoByte;
import com.szbf.util.SerialTool;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;


public class TEST {
    static SerialPort serialPort=null;
    public static String dataOriginal;
    public static String dataport;


    public static void main(String[] args) throws Exception {

        //    byte[] Data5 = HextoByte.hexToByteArray("");
          //  byte[] bytes=hexStrToByteArray("0D");

            SerialTool.findPort();
            serialPort= SerialTool.openPort("COM9");
            SerialTool.addListener(serialPort, new SerialListener());

            Thread.sleep(300);
            byte[] Data = HextoByte.hexToByteArray("43510D");
            SerialTool.sendToPort(serialPort,Data);
            Thread.sleep(1000);
            System.out.println(dataport);
            Thread.sleep(1000);
            dataport=null;

            Thread.sleep(5000);
            byte[] Data1 = HextoByte.hexToByteArray("43520D");
            SerialTool.sendToPort(serialPort,Data1);
            Thread.sleep(1000);
            System.out.println(dataport);


        //    byte[] bytes=hexStrToByteArray("0D");
/*            String a="C\n";
            bytes=a.getBytes("US-ASCII");
            System.out.println(a);*/
           // SerialTool.sendToPort(serialPort,bytes);
            Thread.sleep(1000);

  /*          dataport="";
            byte[] bytes1=null;
            String a1="S\r";
            bytes1=a1.getBytes("US-ASCII");
            System.out.println(a);
            SerialTool.sendToPort(serialPort,bytes1);
            Thread.sleep(1000);

            // int i=0;
            while (true){
                String send="?\r";
                bytes=send.getBytes("US-ASCII");
                System.out.println(send);
                SerialTool.sendToPort(serialPort,bytes);
                if (dataport.contains("00")){
                    System.out.println("执行成功");
                    Thread.sleep(1500);
                    break;
                }
                else if (dataport.contains("04")){
                    System.out.println("正在运动");
                    //int b=1/0;
                }
                Thread.sleep(1000);
            }*/




    }

    public static byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static class SerialListener implements SerialPortEventListener {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            switch (serialPortEvent.getEventType()) {

                case SerialPortEvent.BI: // 10 通讯中断
                    System.out.println("与串口设备通讯中断,错误");
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
                        if (serialPort== null) {
                            //JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("串口对象为空！监听失败！, 错误");
                        }
                        else {
                            data = SerialTool.readFromPort(serialPort);    //读取数据，存入字节数组
                            //System.out.println(new String(data));

                            // 自定义解析过程，你在实际使用过程中可以按照自己的需求在接收到数据后对数据进行解析
                            if (data == null || data.length < 1) {    //检查数据是否读取正确
                                //JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("读取数据过程中未获取到有效数据！请检查设备或程序！, 错误");
                                System.exit(0);
                            }
                            else {

                                dataOriginal = new String(data);    //将字节数组数据转换位为保存了原始数据的字符串
                                dataport+=dataOriginal;
                                Thread.sleep(500);
                                System.out.println("读取的数据为:"+data[0]);
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("读取错误");
                        //JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);    //发生读取错误时显示错误信息后退出系统
                    }

                    break;

            }
        }
    }

}
