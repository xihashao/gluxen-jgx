package com.gluxen.jgx.common.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2017/3/22.
 */
public class UuidUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
