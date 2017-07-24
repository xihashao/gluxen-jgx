package com.gluxen.jgx.common.utils.converter;


import com.gluxen.jgx.common.log.LogTypeEnum;
import com.gluxen.jgx.common.utils.DateUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by cby on 2016/9/5.
 */
public class DateSerializerUtils implements JsonSerializer<Date> {

    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        try {
            return new JsonPrimitive(DateUtils.formatTime(date));
        } catch (ParseException e) {
            LogTypeEnum.DEFAULT.error("日期格式化失败:",e);
        }
        return null;
    }
}
