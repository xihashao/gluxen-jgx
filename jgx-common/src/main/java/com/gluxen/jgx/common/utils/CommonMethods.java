package com.gluxen.jgx.common.utils;

import com.gluxen.jgx.common.constant.CommonConstants;
import java.util.*;

/**
 * Created by cby on 2016/9/8.
 */
public class CommonMethods {
    /***
     * 批量插入前批量list封装
     * @param list
     * @return
     */
    public static Set<List> insertBatchList(List list){
        Set<List> setResult=new LinkedHashSet<List>();
        if(CollectionUtils.isEmpty(list)){
            return setResult;
        }
        int batchCount = CommonConstants.BATCH_INSERT_MAX_NUM;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                setResult.add(list.subList(index, batchLastIndex));
                break;// 数据插入完毕，退出循环
            } else {
                setResult.add(list.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + (batchCount - 1);
            }
        }
        return setResult;
    }


    /***
     *  当返回时map或者list 时候 统一返回 list
     */
    public static List getListByObject(Object o){
        List result=new ArrayList();
        if(o!=null){
            if(o instanceof Map){
                result.add(o);
            }else if(o instanceof List){
                result.addAll((List)o);
            }
        }
        return result;
    }
    
    /**
     * 生成ID
     * @return
     * @author zhou.zhengkun
     * @date 2017年3月17日
     * @version V1.0
     * modify history
     */
    public static String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	  }
}
