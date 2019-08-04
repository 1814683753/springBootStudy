package com.hjl;

import com.hjl.service.MyMailServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootStudyApplicationTests {

    @Autowired
    private MyMailServices myMailServices;

    @Test
    public void testSimpleEmail() {
        // 简单发送邮件测试
        myMailServices.sendEmail("jiale.huang@hand-china.com","测试主题","测试",new String[]{"1814683753@qq.com"});
    }

    @Test
    public void testHtmlEmail() {
        myMailServices.sendHtmlEmail("jiale.huang@hand-china.com","测试主题","<h1>首页</h1><br/><p>内容</p>",new String[]{""});
    }

    @Test
    public void testAttachmentsEmail() {
        myMailServices.sendAttachmentsMail("jiale.huang@hand-china.com","测试主题","<p>请注意查看附件</p>",new String[]{""},"D://note.txt","D://test.jpg");
    }

    @Test
    public void testInlineResourceMail() {
        myMailServices.sendInlineResourceMail("jiale.huang@hand-china.com","测试主题","<p>请注意查看附件</p>",new String[]{""},"D://test.jpg");
    }
}
