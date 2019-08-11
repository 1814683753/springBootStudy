package com.hjl.utils;

import com.hjl.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @Author hjl
 * @Description 读取配置文件的工具类
 * @Date 2019/8/11 18:21
 */
public final class PropertyUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtil.class);

    private static ResourceBundle resource;

    /**
     * 指定读取默认的配置文件
     */
    static {
        try {
            resource = ResourceBundle.getBundle("node");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(Constants.READ_PROPERTIES_EXCEPTION, e);
        }
    }

    /**
     * 根据key读取配置文件中的内容，若为空则返回默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return resource.getString(key) == null ? defaultValue : resource.getString(key);
    }

    /**
     * 根据key读取配置文件中的内容
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return resource.getString(key);
    }
}
