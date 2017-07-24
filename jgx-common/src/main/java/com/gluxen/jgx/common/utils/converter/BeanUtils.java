package com.gluxen.jgx.common.utils.converter;

import com.gluxen.jgx.common.log.LogTypeEnum;
import com.gluxen.jgx.common.utils.DateUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by cby on 2016/9/5.
 * Map 转 bean 或者bean 转map 利器
 */
public class BeanUtils implements Converter {
    public BeanUtils(){
    }

    /***
     * 封装BeanUtils
     * @return
     */
    public static BeanUtilsBean initBeanUtils(){
        BeanUtils dtConverter = new BeanUtils();
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.deregister(Date.class);
        convertUtilsBean.register(dtConverter, Date.class);
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,
                new PropertyUtilsBean());
        return beanUtilsBean;
    }

    public  Object convert(Class type, Object value) {
        return toDate(type,value);
    }

    private Object  toDate(Class type, Object value) {
        if(type==null||value==null||"".equals(value)){
            return null;
        }
        if(type.equals(Date.class)){
            if(value instanceof String){
                String dateValue=value.toString().trim();
                int len=dateValue.length();
                try {
                    if(len<=10){
                        return DateUtils.parseDate(dateValue);
                    }
                    if (len <= 19) {
                        return DateUtils.parseTime(dateValue);
                    }
                } catch (ParseException e) {
                    LogTypeEnum.DEFAULT.error("map数据转bean过程中，日期字符串字段转Date失败:",e);
                }
            }else if(value instanceof Long){
                return new Date((Long) value);
            }
        }
        return value;
    }

}
