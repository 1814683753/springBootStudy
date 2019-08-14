package com.hjl.constant;

/**
 * @Author hjl
 * @Description 用于存放常量的接口
 * @Date 2019/8/3 16:46
 */
public interface Constants {

    String QUERY_EXCEPTION = "查询发送异常: ";

    String DATE_FORMAT_EXCEPTION = "日期格式化异常";

    String DATE_PARSE_EXCEPTION = "日期解析异常";

    String TIMESTAMP_CHANCE_DATE_EXCEPTION = "时间戳转换日期异常：";

    String DATE_CHANCE_TIMESTAMP_EXCEPTION = "时间戳转换日期异常：";

    String READ_PROPERTIES_EXCEPTION = "读取配置文件异常: ";

    /**
     * 服务器返回码配置
     */
    Integer CODE_400 = 400;
}
