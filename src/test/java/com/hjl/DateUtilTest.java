package com.hjl;

import com.hjl.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author hjl
 * @Description 日期工具类测试
 * @Date 2019/8/11 18:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtilTest {

    @Test
    public void testDateUtil() {
        String time = DateUtil.formatDate(new Date(),null);
        System.out.println("time = " + time);
        Timestamp timestamp = DateUtil.dateTranceToTimestamp(new Date());
        System.out.println("timestamp = " + timestamp);
    }
}
