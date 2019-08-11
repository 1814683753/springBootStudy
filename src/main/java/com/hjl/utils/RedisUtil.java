package com.hjl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author hjl
 * @Description 自定义redis工具类
 * @Date 2019/8/11 10:57
 */
public final class RedisUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

    private RedisTemplate<String,Object> redisTemplate;
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 设置缓存失效的时间
     * @param key
     * @param time
     * @return
     */
    public  boolean expire(String key, long time) {
        try {
            if (time>0){
                LOG.info("redisTemplate is null : {}", Objects.isNull(redisTemplate));
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("设置缓存失效时间失败：",e);
        }
        return false;
    }

    /**
     * 获取所有key
     * @param pattern
     * @return
     */
    public  Set<String> getAllKey(String pattern){
        Set<String> keys = new HashSet<>();
        try {
            LOG.info("redisTemplate is null : {}", Objects.isNull(redisTemplate));
            keys = redisTemplate.keys(pattern);

        }catch (Exception e){
            e.printStackTrace();
            LOG.error("设置缓存失效时间失败：",e);
        }
        return keys;
    }



}
