package com.gluxen.jgx.dao.redis;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by cby on 2016/9/1.
 */
@Repository("redisUtils")
public class RedisUtils {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /***
     * redis中设置值
     * @param key
     * @param data
     * @param expired 秒
     * @return
     */
    public boolean setDataByKey(final String key, final String data, final long expired){
        boolean result=false;
        if(StringUtils.isBlank(key)||StringUtils.isBlank(data)){
            return result;
        }
        Boolean flag= (Boolean) stringRedisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                long exTmp=expired<0?0:expired;

                redisConnection.set(key.getBytes(),data.getBytes());
                if(expired>0){
                    redisConnection.expire(key.getBytes(),expired);
                }
                return true;
            }
        });
        if(BooleanUtils.isTrue(flag)){
            result=true;
        }
        return result;
    }
    /***
     * redis 中获取值
     */
    public String getDataByKey(final String key){
         if(StringUtils.isBlank(key)){
             return null;
         }
        String result = (String) stringRedisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                if(redisConnection.get(key.getBytes())==null){
                	return null;
                }
                return new String(redisConnection.get(key.getBytes()));
            }
        });
        return result;
    }
    /***
     * redis 删除
     */
    public Long removeBykey(final String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        Long result = (Long) stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {

                return redisConnection.del(key.getBytes());
            }
        });
        return result;
    }
}
