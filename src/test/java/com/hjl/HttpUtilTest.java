package com.hjl;

import com.hjl.utils.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
            /*String result = HttpClientUtil.post("http://192.168.168.201:6069/api/ps/queryCostCenter");
            System.out.println("result = " + result);*/
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("TaskTitle", "admin"));
            list.add(new BasicNameValuePair("PageIndex", "0"));
            list.add(new BasicNameValuePair("PageSize", "13"));
            list.add(new BasicNameValuePair("orderBy", "0"));
            String result = HttpClientUtil.getRequest("https://crmoem.jsti.com:8029/api/V1/Basic/GetAllSaleTask",list);
            System.out.println("result = " + result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
