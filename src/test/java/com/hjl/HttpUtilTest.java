package com.hjl;

import com.hjl.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hjl
 * @Description httpUtil测试类
 * @Date 2019/8/13 13:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilTest {

    @Test
    public void testHttpUtil() {
        try{
            String result = HttpClientUtil.post("http://192.168.168.201:6069/api/ps/queryCostCenter");
            System.out.println("result = " + result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
