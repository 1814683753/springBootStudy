package com.hjl.utils;

import com.hjl.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @Author hjl
 * @Description 日期工具类
 * @Date 2019/8/11 17:30
 */
public final class DateUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 获取默认发格式化字符串
     */
    private static  String defaultPattern = PropertyUtil.getProperty("defaultPattern");

    /**
     * 对日期进行相应的格式化
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern)) {
                pattern = defaultPattern;
                LOG.info("pattern : {}",pattern);
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(Constants.DATE_FORMAT_EXCEPTION,e);
        }
        return null;
    }

    /**
     * 将指定格式的字符串解析成日期
     * @param date
     * @param pattern
     * @return
     */
    public static Date parseDate(String date,String pattern){
        try {
            if (StringUtils.isEmpty(pattern)) {
                pattern = defaultPattern;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(Constants.DATE_PARSE_EXCEPTION,e);
        }
        return null;
    }

    /**
     * 时间戳转换成日期
     * @param timestamp
     * @return
     */
    public static Date timestampTranceToDate(Timestamp timestamp){
        try{
            if(Objects.nonNull(timestamp)){
                return new Date(timestamp.getTime());
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(Constants.TIMESTAMP_CHANCE_DATE_EXCEPTION,e);
        }
        return null;
    }

    /**
     * 时间戳转换成日期
     * @param date
     * @return
     */
    public static Timestamp dateTranceToTimestamp(Date date){
        try {
            if(Objects.nonNull(date)){
                return new Timestamp(date.getTime());
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(Constants.DATE_CHANCE_TIMESTAMP_EXCEPTION,e);
        }
        return null;
    }

}
