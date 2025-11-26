package com.szbf.util;

import com.szbf.constant.GetValue;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName GetUtil.java
 * @Description TODO
 * @createTime 2022年10月24日 15:08:00
 */
public class GetUtil {
    public static String GetValueToGetReturnValue(String s,String type){
        String returnValue = "";
        for (String gh1:
        GetValue.GetHandler1) {
            if (gh1.equals(type)){
                boolean isStart=false;
                char[] chars = s.toCharArray();
                for (int i=0;i<chars.length;i++){
                    if (chars[i]=='0'&&!isStart||i>=chars.length-2){
                        continue;
                    }else {
                        isStart=true;
                        returnValue+=chars[i];
                    }
                }
                if (returnValue.equals("")){
                    double v=0;
                    returnValue =String.valueOf(v);
                }else {
                    double v = Double.parseDouble(returnValue);
                    double v1 = v / 10;
                    returnValue = String.valueOf(v1);
                }
            }
        }
        if (returnValue.equals("")) {
            for (String gh2 :
                    GetValue.GetHandler2) {
                if (gh2.equals(type)) {
                    boolean isStart=false;
                    char[] chars = s.toCharArray();
                    for (int i=0;i<chars.length;i++){
                        if (chars[i]=='0'&&!isStart||i>=chars.length-2){
                            continue;
                        }else {
                            isStart=true;
                            returnValue+=chars[i];
                        }
                    }
                    if (returnValue.equals("")){
                        double v=0;
                        returnValue =String.valueOf(v);
                    }else {
                        double v = Double.parseDouble(returnValue);
                        double v1 = v / 100;
                        returnValue = String.valueOf(v1);
                    }
                }
            }
        }

        if (returnValue.equals("")) {
        for (String gh4:
                GetValue.GetHandler4) {
            if (gh4.equals(type)){
                if (s!=null){
                    double i = Double.parseDouble(s);
                    boolean gh=i!=0;
                    returnValue= String.valueOf(gh);
                }
            }
        }
        }
        return returnValue;
    }
}
