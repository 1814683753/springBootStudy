package com.hjl.job;

import com.hjl.service.MyMailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
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
    @Value("${to}")
    private String to ;
    @Value("${cc}")
    private String cc ;
    /**
     * 抄送人数组
     */
    private String[] ccs ;

    @Scheduled(cron = "0 * * * * *")
    public void test(){
        try{
            LOG.info("定时任务正在执行......现在时间是： {}",new Date());
            getValue();
            mailServices.sendHtmlEmail(to,"test","<p>测试......</P>",ccs);
            LOG.info("定时任务执行结束......现在时间是： {}",new Date());
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("发送邮件发生异常.....",e);
        }

    }

    @Scheduled(cron = "0 0 9,14,20 * * *")
    public void study(){
        try {
            LOG.info("定时任务正在执行......现在时间是： {}",new Date());
            getValue();
            mailServices.sendHtmlEmail(to,"study","<p>开始学习......</P>",ccs);
            LOG.info("定时任务执行结束......现在时间是： {}",new Date());
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("发送邮件发生异常.....",e);
        }

    }
    @Scheduled(cron = "0 30 11,18,22 * * *")
    public void play(){
        try {
            LOG.info("定时任务正在执行......现在时间是： {}",new Date());
            getValue();
            mailServices.sendHtmlEmail("1814683753@qq.com","play","<p>休息一下马上回来......</P>",new String[]{});
            LOG.info("定时任务执行结束......现在时间是： {}",new Date());
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("发送邮件发生异常.....",e);
        }

    }

    /**
     * 设置值
     */
    private void getValue(){
        try{
            if(StringUtils.isEmpty(to)){
                // 为空则采用默认值
                to = "1814683753@qq.com";
            }
            if(StringUtils.isEmpty(cc)){
                ccs = new String[]{};
            }else{
                ccs = cc.split(",");
            }
            LOG.info("to： {}, css : {}",to, Arrays.toString(ccs));
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("处理数据发生异常：",e);
        }

    }


}
