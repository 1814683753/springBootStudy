package com.hjl;

import com.hjl.constant.Constants;
import com.hjl.utils.XmlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ：hjl
 * @date ：2019/9/29 10:42
 * @description：
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlUtilsTest {

    @Test
    public void testXmlUtilCreate() {
        Element rootElement = XmlUtils.createRootTag("root");
        Map<String,String> map = new HashMap<>();
        map.put("param1","a");
        XmlUtils.createTag(rootElement,"first",map,"测试标签1");
        boolean flag = XmlUtils.createFile(rootElement,"D:\\test\\test.xml");
        System.out.println("flag = " + flag);
    }
    @Test
    public void testXmlUtilParse() {
        Element rootElement = XmlUtils.parseXmlFile("D:\\test\\test.xml");
        String tagName = XmlUtils.getRootTagName(rootElement);
        System.out.println("tagName = " + tagName);
        String content = XmlUtils.getTagText(rootElement,"first", Constants.PARAM_TYPE,"param1");
        System.out.println("content = " + content);
    }
}
