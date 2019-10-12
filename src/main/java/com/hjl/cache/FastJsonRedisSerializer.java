package com.hjl.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author ：hjl
 * @date ：2019/10/11 9:30
 * @description： redis序列化类
 * @modified By：
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private Logger logger = LoggerFactory.getLogger(FastJsonRedisSerializer.class);

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        // 尤其是redis这个序列的支持，在类中要加保证一默认的构造方法，否则在进行反序列化时，fastjosn根据typeName进行反序列化，会发生该异常
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        logger.info("t=={}",t);
        return JSON.toJSONString(t, SerializerFeature.WriteNullStringAsEmpty).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String str = new String(bytes, DEFAULT_CHARSET);
        logger.info("str=={}",str);
        return JSON.parseObject(str, clazz);
    }
}
