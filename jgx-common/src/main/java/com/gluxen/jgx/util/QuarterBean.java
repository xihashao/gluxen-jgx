package com.gluxen.jgx.util;

import java.text.MessageFormat;

/**
 * 
 * 季度处理
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class QuarterBean {
	public final static int QUARTERS_EVERY_YEAR = 4;    
    private final static String[] QUARTERS = {
            "{0}年1季度",
            "{0}年2季度",
            "{0}年3季度",
            "{0}年4季度"       
        };
    
    private int year;
    private int quarter;    
    
    public QuarterBean(int year, int quarter) {
        if(quarter < 1 || quarter > QUARTERS_EVERY_YEAR) {
            throw new IllegalArgumentException("quarter is invalid.");
        }
        this.year = year;
        this.quarter = quarter;
    }
    
    private QuarterBean() {
        
    }
    
    public int getYear() {
        return year;
    }
    public int getQuarter() {
        return quarter;
    }    
    
    /**
     * 获得上一季度
     * @return
     */
    public QuarterBean getPastQuarter() {
        QuarterBean bean = new QuarterBean();
        int y = (QUARTERS_EVERY_YEAR + 1 - quarter) / QUARTERS_EVERY_YEAR;
        bean.year = year - y;
        bean.quarter = y * QUARTERS_EVERY_YEAR + (quarter - 1);
        return bean;
    }
    
    public String getQuarters() {
        return MessageFormat.format(QUARTERS[quarter - 1], String.valueOf(year));
    }
}
