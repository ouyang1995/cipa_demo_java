package com.szbf.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName StringUtil.java
 * @Description TODO
 * @createTime 2022年07月22日 16:37:00
 */
public class StringUtil {
    public static String streamToStr(InputStream inputStream){

        StringBuilder builder=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String con;
            while ((con=br.readLine())!=null){
                builder.append(con);
            }

            br.close();
            return builder.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }

}
