package com.hjl.service;

/**
 * @Author hjl
 * @Description 邮件发送的服务层
 * @Date 2019/8/4 10:45
 */
public interface MyMailServices {

    /**
     * 简单邮件发送
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     */
    void sendEmail(String to, String subject, String content,String[] Cc);
    /**
     * 发送html格式的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     */
    void sendHtmlEmail(String to, String subject, String content,String[] Cc);

    /**
     * 发送带有附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     * @param paths 附件地址
     */
    void sendAttachmentsMail(String to, String subject, String content,String[] Cc,String... paths);

    /**
     * 发送含有静态资源的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param Cc 抄送人数组
     * @param paths 静态文件地址
     */
    void sendInlineResourceMail(String to, String subject, String content,String[] Cc,String... paths);


}
