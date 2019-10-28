package com.hjl.service.impl;

import com.hjl.constant.Constants;
import com.hjl.dao.FileManagerDao;
import com.hjl.entity.Files;
import com.hjl.service.FileManageService;
import com.hjl.utils.CompressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    @Value("${dirPath}")
    private String path;
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
    /**
     * 下载文件
     * @param fileName
     * @param response
     */
    @Override
    public void download(HttpServletResponse response, String fileName) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            LOG.info("dir path : {}",path);
            // 获取所有文件所在的根目录
            File dir = new File(path);
            if (dir.exists()&&dir.isDirectory()){
                File[] files = dir.listFiles();
                List<File> targetFiles = new ArrayList<>();
                if (files!=null && files.length>0){
                    getFileByName(files,fileName,targetFiles);
                }
                if (!StringUtils.isEmpty(targetFiles)){
                    // 取出第一个即可
                    File file = targetFiles.get(0);
                    // 配置文件下载
                    response.setHeader("content-type", "application/octet-stream");
                    response.setContentType("application/octet-stream");
                    // 下载文件能正常显示中文
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    // 实现文件下载
                    byte[] buffer = new byte[1024];
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    os = response.getOutputStream();
                    int len = -1 ;
                    while ((len=bis.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    LOG.info("Download the song successfully!");
                }
            }else {
                LOG.info("文件目录目录{}不存在......",dir.getAbsolutePath());
            }
        }catch (Exception e){
            LOG.error("download happen a error : ",e);
        }finally {
            if (os !=null){
                try {
                    os.close();
                }catch (Exception e){
                    LOG.error("close failed : ",e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    LOG.error("close failed : ",e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    LOG.error("close failed : ",e);
                }
            }
        }
    }

    /**
     * 下载文件
     *
     * @param response
     * @param dirName  文件夹名
     */
    @Override
    public void downloadDir(HttpServletResponse response, String dirName) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            File dir = new File(path);
            List<File> targetFiles = new ArrayList<>();
            getDirByName(dir.listFiles(),dirName,targetFiles);
            if (!CollectionUtils.isEmpty(targetFiles)){
                // 只取第一个
                File targetDir = targetFiles.get(0);
                // 压缩文件
                boolean isSuccess = CompressUtils.dirToZip(targetDir.getAbsolutePath());
                if (isSuccess){
                    File downloadFile = new File(targetDir.getParent()+File.separator+targetDir.getName()+"zip");
                    // 配置文件下载
                    response.setHeader("content-type", "application/octet-stream");
                    response.setContentType("application/octet-stream");
                    // 下载文件能正常显示中文
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFile.getName(), "UTF-8"));
                    // 实现文件下载
                    byte[] buffer = new byte[1024];
                    fis = new FileInputStream(downloadFile);
                    bis = new BufferedInputStream(fis);
                    os = response.getOutputStream();
                    int len = -1 ;
                    while ((len=bis.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    LOG.info("Download the song successfully!");
                }
            }else {
                LOG.info("当前路径{}下不存在{}目录",dir,targetFiles);
            }
        }catch (Exception e){
            LOG.error("download dir happen a error : ",e);
        }finally {
            if (os !=null){
                try {
                    os.close();
                }catch (Exception e){
                    LOG.error("close failed : ",e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    LOG.error("close failed : ",e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    LOG.error("close failed : ",e);
                }
            }
        }
    }

    /**
     * 遍历文件目录获取指定文件
     * @param files 要遍历的文件目录
     * @param name 要查询的文件名
     * @param targetFiles 存放查找到的目标文件
     * @return 若没找到则返回null
     */
    private static void getFileByName(File[] files, String name, List<File> targetFiles){
        for (File file: files) {
            if (file.isDirectory()){
                getFileByName(file.listFiles(),name,targetFiles);
            }else {
                String fileName = file.getName();
                if (fileName.endsWith(name)){
                    LOG.info("path:{},name:{}",file.getAbsolutePath(),fileName);
                    targetFiles.add(file);
                }
            }
        }
    }

    /**
     * 遍历文件目录获取指定文件目录
     * @param files 要遍历的文件目录
     * @param name 要查询的文件目录
     * @param targetFiles 存放查找到的目标目录
     */
    private static void getDirByName(File[] files, String name, List<File> targetFiles){
        for (File file: files) {
            if (file.isFile()){
                getFileByName(file.listFiles(),name,targetFiles);
            }else {
                String fileName = file.getName();
                if (fileName.endsWith(name)){
                    LOG.info("path:{},name:{}",file.getAbsolutePath(),fileName);
                    targetFiles.add(file);
                }
            }
        }
    }
}
