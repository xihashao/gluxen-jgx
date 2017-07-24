package com.gluxen.jgx.util;

/**
 * mvc返回数据封装
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class PutResult {
    private boolean success;
    private String msg;
    private Object otherData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public static PutResult genResult(boolean success, String msg) {
        PutResult r = new PutResult();
        r.setSuccess(success);
        r.setMsg(msg);
        return r;
    }

    public static PutResult genResult(boolean success, String msg,Object otherData) {
        PutResult r = new PutResult();
        r.setSuccess(success);
        r.setMsg(msg);
        r.setOtherData(otherData);
        return r;
    }
}
