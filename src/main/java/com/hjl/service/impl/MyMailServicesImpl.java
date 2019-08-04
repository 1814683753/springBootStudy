package com.hjl.service.impl;

import com.hjl.service.MyMailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author hjl
 * @Description 邮件发送的实现类
 * @Date 2019/8/4 10:49
 */
@Service
public class MyMailServicesImpl implements MyMailServices {
    private final Logger logger = LoggerFactory.getLogger(MyMailServicesImpl.class);

    /**
     * spring 提供的邮件发送类
     */
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${from}")
    private String from;

    /**
     * 简单邮件发送
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     */
    @Override
    public void sendEmail(String to, String subject, String content,String[] Cc) {
        logger.info("from=={}",from);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置发件人
        mailMessage.setFrom(from);
        // 设置收件人
        mailMessage.setTo(to);
        // 设置主题
        mailMessage.setSubject(subject);
        // 设置内容
        mailMessage.setText(content);
        if(Cc!=null && Cc.length>0){
            // 设置抄送人
            mailMessage.setCc(Cc);
        }
        try {
            // 发送邮件
            long start = System.currentTimeMillis();
            javaMailSender.send(mailMessage);
            long end = System.currentTimeMillis();
            logger.info("consumer time : {} ms",(end-start));
        }catch (Exception e){
            logger.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 发送html格式的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     */
    @Override
    public void sendHtmlEmail(String to, String subject, String content,String[] Cc){
        // 创建一个MINE消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            warp(messageHelper,to,Cc,subject,content,true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            logger.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 发送带有附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     * @param paths 附件地址
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content,String[] Cc,String... paths){
        // 创建一个MINE消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            warp(messageHelper,to,Cc,subject,content,true);
            for (String path: paths) {
                File file = new File(path);
                String fileName = file.getName();
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                // 添加附件名，以及文件
                messageHelper.addAttachment(fileName,fileSystemResource);
            }
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            logger.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 发送含有静态资源的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     * @param paths 静态文件地址
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content,String[] Cc,String... paths){
        // 创建一个MINE消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            warp(messageHelper,to,Cc,subject,content,true);
            for (String path: paths) {
                File file = new File(path);
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                messageHelper.addInline(file.getName(),fileSystemResource);
            }
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            logger.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 包装对象的方法
     * @param messageHelper 要包装的类
     * @param to 收件人
     * @param Cc 抄送人
     * @param subject 主题
     * @param content 内容
     * @param isHtml 是否是html内容
     */
    private void warp(MimeMessageHelper messageHelper,String to,String[] Cc, String subject, String content,boolean isHtml){
        try {
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            if(Cc!=null && Cc.length>0){
                messageHelper.setCc(Cc);
            }
            // true代表内容支持html格式
            messageHelper.setText(content,isHtml);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }

    }
}

