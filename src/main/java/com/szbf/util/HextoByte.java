package com.szbf.util;

import org.springframework.stereotype.Service;

public class HextoByte {

    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    public static String bytesToHexString(byte[] src) {

        StringBuilder builder = new StringBuilder();

        if (src == null || src.length <= 0) {

            return null;

        }

        String hv;

        for (int i = 0; i < src.length; i++) {

// 以十六进制(基数 16)无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写

            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();

            if (hv.length() < 2) {

                builder.append(0);

            }

            builder.append(hv);

        }

        return builder.toString();

    }

    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }
}
