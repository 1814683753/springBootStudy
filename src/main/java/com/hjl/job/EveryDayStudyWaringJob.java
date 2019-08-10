package com.hjl.job;

import com.hjl.service.MyMailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author hjl
 * @Description 每日计划提醒的定时job
 * @Date 2019/8/10 11:11
 */
@Component
public class EveryDayStudyWaringJob {

    private static final Logger LOG = LoggerFactory.getLogger(EveryDayStudyWaringJob.class);
    @Autowired
    private MyMailServices mailServices;

    /*@Scheduled(cron = "0 * * * * *")
    public void test(){
        LOG.info("定时任务正在执行......"+new Date());
        mailServices.sendHtmlEmail("1814683753@qq.com","test","<p>测试......</P>",new String[]{});
    }*/

    @Scheduled(cron = "0 0 9,14,20 * * *")
    public void study(){
        mailServices.sendHtmlEmail("1814683753@qq.com","play","<p>开始学习......</P>",new String[]{});
    }
    @Scheduled(cron = "0 30 11,18,22 * * *")
    public void play(){
        mailServices.sendHtmlEmail("1814683753@qq.com","study","<p>休息一下马上回来......</P>",new String[]{});
    }


}
