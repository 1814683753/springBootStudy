package com.hjl.comman.demo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/9 14:52
 * @description：
 * @modified By：
 */
public class FtpDemo {
    private static final Logger LOG = LoggerFactory.getLogger(FtpDemo.class);
    public static void main(String[] args) throws Exception {
        FTPClient ftpClient = new FTPClient();
        // 设置主机以及端口号，注意端口号不能为22
        // 22端口不是FTP协议使用的，是SSH2协议的端口号
        ftpClient.connect("192.168.192.129", 21);
        // 设置编码
        ftpClient.setControlEncoding("UTF-8");
        // 设置登陆账号以及密码
        ftpClient.login("lege", "Hero1997");
        // 设置文件的传输类型，默认是ASCII，修改为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // 获取当前的工作目录
        String path = ftpClient.printWorkingDirectory();
        LOG.info("home path : {}",path);
        String imagePath = "/home/lege";
        LOG.info("pic path : {}",imagePath);
        // 切换ftp的目录
        Boolean flag = ftpClient.changeWorkingDirectory(new String(imagePath.getBytes(), "ISO-8859-1"));
        List<String> names = new ArrayList<>();
        LOG.info("是否切换成功：{},当前目录为:{}",flag,ftpClient.printWorkingDirectory());
        if (flag) {
            // 这个方法的意思就是每次数据连接之前,ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            FTPFile[] files = ftpClient.listFiles();
            LOG.info("file total :{}",files.length);
            for (FTPFile tempFile : files) {
                LOG.info("file name:{}",tempFile.getName());
                if (tempFile.isFile()) {
                    String downFileName = new String(tempFile.getName().getBytes("UTF-8"), "iso-8859-1");
                    if (downFileName.endsWith(".jpg") || downFileName.endsWith(".png")
                            || downFileName.endsWith(".gif")) {
                        LOG.info("download pic name:{}",tempFile.getName());
                        names.add(downFileName);
                        File file = new File("d://" + downFileName);
                        OutputStream write = new FileOutputStream(file);
                        ftpClient.retrieveFile(tempFile.getName(), write);
                        write.close();
                    }
                }
            }
        } else {
            LOG.error("切换目录失败");
        }
        ftpClient.disconnect();
    }
}
