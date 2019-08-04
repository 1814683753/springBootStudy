package com.hjl.controller;

import com.hjl.entity.Files;
import com.hjl.service.FileManageService;
import com.hjl.service.MyMailServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author hjl
 * @Description controller
 * @Date 2019/8/3 17:16
 */
@Controller
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileManageService fileManageService;
    @Autowired
    private MyMailServices myMailServices;

    /**
     * 取出所有数据
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Files> queryAll(){
        myMailServices.sendEmail("jiale.huang@hand-china.com","测试","测试",new String[]{});
        LOG.info("start query all...........");
        return fileManageService.queryAllFiles();
    }

}
