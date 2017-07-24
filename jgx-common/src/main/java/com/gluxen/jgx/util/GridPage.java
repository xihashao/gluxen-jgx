package com.gluxen.jgx.util;

import java.util.List;

/**
 * 分页封装类
* @author lishiqiang
* @date 2017-3-15
* @param <T>
* modify history
 */
public class GridPage<T> {
    private Long start = 0L;  //起始
    private Long limit = 20L;  //每页显示个数

    private String query;

    private boolean success=true;
    private Integer results;
    private List<T> rows;
    private List<String> titles;

    private String msg;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
