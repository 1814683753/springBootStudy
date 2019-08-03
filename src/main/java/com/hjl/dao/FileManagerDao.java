package com.hjl.dao;

import com.hjl.entity.Files;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author hjl
 * @Description 操作文件的dao层
 * @Date 2019/8/3 16:48
 */
@Repository
public interface FileManagerDao {
    /**
     * 查询出所有文件信息
     * @return
     */
    List<Files> queryAllFile();
}
