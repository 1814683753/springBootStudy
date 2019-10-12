package com.hjl;

import com.hjl.entity.SecDef;
import com.hjl.service.XmlManageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/11 9:48
 * @description： 缓存测试类
 * @modified By：
 */
public class CacheDemoTest {
    @Autowired
    private XmlManageService xmlManageService;

    @Test
    public void testQuery() {
        List<SecDef> list =  xmlManageService.queryAll();
        list.forEach(secDef -> System.out.println(secDef));
    }
}
