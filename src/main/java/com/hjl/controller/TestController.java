package com.hjl.controller;

import com.hjl.entity.SecDef;
import com.hjl.service.XmlManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/11 10:08
 * @description： 测试
 * @modified By：
 */
@Controller
public class TestController {
    @Autowired
    private XmlManageService xmlManageService;

    /**
     * 取出所有数据
     */
    @RequestMapping("/test/queryAll")
    @ResponseBody
    public List<SecDef> queryAll(){
        return xmlManageService.queryAll();
    }
}
