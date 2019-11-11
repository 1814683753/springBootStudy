package com.hjl.comman.demo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.net.URL;

/**
 * @author ：hjl
 * @date ：2019/11/11 14:50
 * @description： 根据url下载文件demo
 * @modified By：
 */
public class DownloadUrlToFileDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://cn.vuejs.org/js/vue.js");
            String filePath = url.getFile();
            if (StringUtils.isNotEmpty(filePath)){
                String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
                System.out.println(fileName);
                File targetFile = new File("D:\\document\\work\\" + fileName);
                FileUtils.copyURLToFile(url,targetFile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
