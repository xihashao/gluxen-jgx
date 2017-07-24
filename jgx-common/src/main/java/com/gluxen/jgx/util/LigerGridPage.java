package com.gluxen.jgx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* 分页封装类
* @author lishiqiang
* @date 2017-3-15
* @param <T>
* modify history
 */
public class LigerGridPage<T> {

    private Integer page = 1;

    private Integer pagesize = 0;

    private Long Total = 0L;

    private List<T> Rows;

    private Integer minIndex;

    private Integer maxIndex;

    private String query = "";

    private Map<String, Object> resultMap = new HashMap<String, Object>();

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = Math.max(1, page);
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Long getTotal() {
        return Total;
    }

    public void setTotal(Long total) {
        Total = total;
    }

    public List<T> getRows() {
        return Rows;
    }

    public void setRows(List<T> rows) {
        Rows = rows;
    }

    public Integer getMinIndex() {
        return (page-1)*pagesize;
    }

    public void setMinIndex(Integer minIndex) {
        this.minIndex = minIndex;
    }

    public Integer getMaxIndex() {
        return page*pagesize;
    }

    public void setMaxIndex(Integer maxIndex) {
        this.maxIndex = maxIndex;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Object> getResultMap() {
        resultMap.put("Total", getTotal());
        resultMap.put("page", getPage());
        resultMap.put("Rows", getRows());
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
