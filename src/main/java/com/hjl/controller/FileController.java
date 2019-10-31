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

import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/api/queryAll")
    @ResponseBody
    public List<Files> queryAll(){
        /// myMailServices.sendEmail("jiale.huang@hand-china.com","测试","测试",new String[]{});
        LOG.info("start query all...........");
        return fileManageService.queryAllFiles();
    }

    /**
     * 根据文件名下载单个文件
     * @param response
     * @param fileName
     */
    @ResponseBody
    @RequestMapping("/api/downloadFileByName")
    public void downloadFileByName(HttpServletResponse response, String fileName){
        LOG.info("文件{}即将被下载......",fileName);
        fileManageService.download(response,fileName);
        LOG.info("文件{}下载完成......",fileName);
    }

    @ResponseBody
    @RequestMapping("/api/downloadDirByName")
    public void downloadDirByName(HttpServletResponse response, String dirName){
        LOG.info("文件目录{}即将被下载......",dirName);
        fileManageService.downloadDir(response,dirName);
        LOG.info("文件目录下载完成......",dirName);
    }
}
