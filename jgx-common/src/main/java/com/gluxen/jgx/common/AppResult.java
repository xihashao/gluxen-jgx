package com.gluxen.jgx.common;


import com.gluxen.jgx.common.utils.TimestampProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */
public class AppResult<T> implements Serializable{
    private static final long serialVersionUID = -4642737454351895021L;
    private boolean state;//状态 0、1

    private String msg;//成功-失败信息 success error


    private T result;

    private T result2;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }



    public AppResult() {
    }
    /**
     * <p>description:操作成功</p>
     * @return Result
     * @author Wen Yugang
     * @date 2017-3-20下午2:38:13
     */
    public static AppResult success(){
        AppResult<Object> result = new AppResult<Object>();
        result.setState(true);
        result.setMsg("success");
        result.setMsg("操作成功！");
        return result;
    }
    /**
     * <p>description:操作失败</p>
     * @return Result
     * @author Wen Yugang
     * @date 2017-3-20下午2:38:38
     */
    public static AppResult error(){
        AppResult result = new AppResult();
        result.setState(false);
        result.setMsg("error");
        result.setMsg("操作失败！");
        result.setResult("");
        return result;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static String turnJson(Boolean state, String msg, Object o) {
        JSONObject obj = new JSONObject();
        obj.put("state", state);
        obj.put("msg", msg);
        if (o instanceof List) {
            o = toJsonArray((List) o);
        } else if (o instanceof Map) {
            o = toJsonObject((Map) o);
        } else if (o == null) {
            o = "";
        } else {
            o = o.toString();
        }
        obj.put("result", o);
        String result = obj.toString();
        return result;
    }

    public static JSONObject toJsonObject(Map map) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class,
                new TimestampProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject resJson = JSONObject.fromObject(map, config);
        return resJson;
    }

    public static JSONArray toJsonArray(List list) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class,
                new TimestampProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray resJson = JSONArray.fromObject(list, config);
        return resJson;
    }


    public T getResult2() {
        return result2;
    }

    public void setResult2(T result2) {
        this.result2 = result2;
    }
}
