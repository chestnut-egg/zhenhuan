package com.example.zhenhuan.tool;

public class Utils {

    public static int StringToInt(String s){
        int num = -1;
        try {
            num = Integer.parseInt(s);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return num;
    }


}
