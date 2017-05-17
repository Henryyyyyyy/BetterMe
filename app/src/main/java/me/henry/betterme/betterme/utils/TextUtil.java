package me.henry.betterme.betterme.utils;

import java.util.ArrayList;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.utils
 */

public class TextUtil {
    //判断空字符串
    public static boolean isValidate(String name){
        if (name!=null&&!"".equals(name.trim())){
            return true;
        }
        return false;
    }
    public static boolean isValidate(ArrayList list){
        if (list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
}
