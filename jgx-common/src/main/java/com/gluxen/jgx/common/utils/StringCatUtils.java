package com.gluxen.jgx.common.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by cby on 2016/8/30.
 */
public class StringCatUtils {
    public static String getObjs(String sep,Object ...strs){
         StringBuffer sb=new StringBuffer();
        if(ArrayUtils.isNotEmpty(strs)){
             int flag=0;
             for(Object o:strs){
                 if(o!=null){
                     sb.append(o);
                     if(StringUtils.isNotBlank(sep)&&(flag!=strs.length-1)){
                         sb.append(sep);
                     }
                 }
                 flag++;
             }

        }
        return sb.toString();
    }
    public static String getStrs(String ...strs){
        StringBuffer sb=new StringBuffer();
        if(ArrayUtils.isNotEmpty(strs)){
            for(Object o:strs){
                if(o!=null){
                    sb.append(o);
                }
            }
        }
        return sb.toString();
    }
    public static String getObjs(Object ...strs){
        return getObjs(null,strs);
    }
}
