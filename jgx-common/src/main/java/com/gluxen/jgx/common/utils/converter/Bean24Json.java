package com.gluxen.jgx.common.utils.converter;

import com.gluxen.jgx.common.utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by cby on 2016/9/5.
 */
public class Bean24Json<T> {
    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    public void setGsonBuilder(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    private Gson gson;
    private GsonBuilder gsonBuilder = null;
    private Type type;
    private Type typeMap;
    public Bean24Json(){
        gson = new Gson();
        gsonBuilder = new GsonBuilder();
    }
     public   T getBeanByJson(String text,Class<T> tClass){
         //一般情况下，我们从服务器返回来的数据是json数据，所以我们需要把它转换成bean对应的实体类
         //这里有一点要注意的是，我们进行一般的转换，需要json和bean的属性数量和属性类型和名称都要对应完全一致
         //否则可能会报错
         T t = gson.fromJson(text, tClass);//将json数据转换成user类实体
         return t;
     }
     public String getJsonByBean(T t){
         String json = gson.toJson(t);
         return json;
     }
     // 泛型List -> Json
     public String  getJsonByListBean(List<T> list){
        String json = gson.toJson(list, new TypeToken<List<T>>(){}.getType());
        return json;
    }
    // Json -> 泛型List
    public List<T> getListBeanbyJson(String json){
        List<T> tList = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
        return tList;
    }
    // 泛型Map -> Json
    public String getJsonByMap(Map<String,T> condition){
        String json = gson.toJson(condition, new TypeToken<Map<String, T>>(){}.getType());
        return json;
    }
    // Json -> Map
    //new TypeToken<Map<String,Object>>(){}.getType() 这个是t 参数
    public Map<String, T> getMapByJson(String json,Type t){
        Map<String, T> result = gson.fromJson(json,t);
        return result;
    }
    //// Bean(带日期属性) -> Json
    public String getJsonByBeanContainDate(T t){
        gson = gsonBuilder/*.serializeNulls()*/. /*excludeFieldsWithModifiers(Modifier.TRANSIENT).*/ /*registerTypeAdapter(java.util.Date.class,
                new DateSerializerUtils()).*/setDateFormat(DateUtils.getYMDHMS()).create();
        String json = gson.toJson(t,type);
        return json;
    }
    // Json -> 泛型日期List
    public List<T> getListContainDateByJson(String json, Type t){
        gson = gsonBuilder/*.registerTypeAdapter(java.util.Date.class,
                new DateDeserializerUtils())*/.setDateFormat(DateUtils.getYMDHMS()).create();
        List<T> result = gson.fromJson(json, t);
        return result;
    }
    public static void main(String st[]){
        Bean24Json<TestBean> beanJsonConverter=new Bean24Json<TestBean>();
        List<TestBean> testBeanList=new ArrayList<TestBean>();
        TestBean tb=new TestBean();
        tb.setAge(19);
        tb.setName("lzy");
        tb.setBirth(new Date());
        testBeanList.add(tb);
        //String s= beanJsonConverter.getJsonByBeanContainDate(testBeanList);
        String json="[{\"age\":19,\"name\":\"lzy\",\"birth\":\"2016-09-05 14:56:48\"}]";
        Type type = new TypeToken<List<TestBean>>(){}.getType();
        List<TestBean> list=(List<TestBean>)beanJsonConverter.getListContainDateByJson(json,new TypeToken<List<TestBean>>(){}.getType());
        for(TestBean t:list){
            System.out.println(t.getBirth());
        }

    }
}

class TestBean {
    private int age;
    private  String name;
    private Date birth;
    private String agc;

    public String getAgc() {
        return agc;
    }

    public void setAgc(String agc) {
        this.agc = agc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }


}
