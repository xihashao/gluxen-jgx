package com.gluxen.jgx.common.utils.converter;

import com.gluxen.jgx.common.log.LogTypeEnum;
import com.gluxen.jgx.common.utils.DateUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by cby on 2016/9/5.
 */
public class DateDeserializerUtils implements JsonDeserializer<Date> {

    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return DateUtils.parseTime(json.getAsJsonPrimitive().getAsString());
        } catch (ParseException e) {
            LogTypeEnum.DEFAULT.error("日期格式化失败:",e);
        }
        return null;
    }
}
