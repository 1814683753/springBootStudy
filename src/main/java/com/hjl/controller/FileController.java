package com.hjl.controller;

import com.hjl.entity.Files;
import com.hjl.service.FileManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author hjl
 * @Description TODO
 * @Date 2019/8/3 17:16
 */
@Controller
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileManageService fileManageService;

    /**
     * 取出所有数据
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Files> queryAll(){
        LOG.info("start query all...........");
        return fileManageService.queryAllFiles();
    }

}
