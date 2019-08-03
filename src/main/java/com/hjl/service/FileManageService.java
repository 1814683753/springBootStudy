package com.hjl.service;

import com.hjl.entity.Files;

import java.util.List;

/**
 * @Author hjl
 * @Description 文件管理的服务层
 * @Date 2019/8/3 17:07
 */
public interface FileManageService {

    /**
     * 查询出所有的文件
     * @return
     */
    List<Files> queryAllFiles();
}
