package com.szbf.util;

import org.apache.commons.collections.MultiMap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtReadline {

    public static Map<String,String> sample_status=new HashMap<>();
        public static void SampleManagement(File file){
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                String s;
                while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    if (s.contains("Sample")){
                        sample_status.put(s.substring(0,s.indexOf("=")),s.substring(s.indexOf("=")+1));
                    }
            }
                Thread.sleep(2000);
                br.close();

            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public static boolean writetxtfile(String Content,String filepath) {
        boolean flag=false;
        try {
            //写入的txt文档的路径
            PrintWriter pw=new PrintWriter(filepath);
            //写入的内容
            pw.write(Content);
            pw.flush();
            pw.close();
            flag=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
