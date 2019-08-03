package com.hjl.service.impl;

import com.hjl.constant.Constants;
import com.hjl.dao.FileManagerDao;
import com.hjl.entity.Files;
import com.hjl.service.FileManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author hjl
 * @Description 文件管理的具体实现
 * @Date 2019/8/3 17:10
 */
@Service
public class FileManageServiceImpl implements FileManageService {

    /**
     * 引入日志打印类
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileManageServiceImpl.class);
    /**
     * 引入dao
     */
    @Autowired
    private FileManagerDao fileManagerDao;
    /**
     * 查询出所有的文件
     * @return
     */
    @Override
    public List<Files> queryAllFiles() {
        try{
            List<Files> filesList = fileManagerDao.queryAllFile();
            if(!CollectionUtils.isEmpty(filesList)){
                for (Files file: filesList) {
                    LOG.info("[id={},name={}]",file.getFileId(),file.getFileName());
                }
            }
            return filesList;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(Constants.QUERY_EXCEPTION,e);
        }
        return null;
    }
}
