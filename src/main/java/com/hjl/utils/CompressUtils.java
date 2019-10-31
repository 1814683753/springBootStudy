package com.hjl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author ：hjl
 * @date ：2019/10/25 17:20
 * @description：文件压缩工具类
 * @modified By：
 */
public class CompressUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CompressUtils.class);
    private static final int  BUFFER_SIZE = 2 << 10;

    /**
     * 单个文件压缩成zip文件
     * @param filePath
     * @return
     */
    public static boolean toZip(String filePath){
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()){
            return Boolean.FALSE;
        }
        try(
                FileInputStream fi = new FileInputStream(sourceFile);
                // 创建zip文件流
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(sourceFile.getParent()+File.separator+sourceFile.getName()+".zip")));
                ) {
            if(sourceFile.isFile()){
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                // copy文件到zip流
                int len;
                byte[] buf = new byte[BUFFER_SIZE];
                while ((len=fi.read(buf))!=-1){
                    zos.write(buf,0,len);
                }
            }
            LOG.info("压缩完成........");
            return Boolean.TRUE;
        }catch (Exception e){
            LOG.error("压缩失败.....",e);
        }
        return Boolean.FALSE;
    }

    /**
     * 将目录打包成zip文件
     * @param filePath
     * @return
     */
    public static boolean dirToZip(String filePath){
        File dir = new File(filePath);
        if (!dir.exists() || dir.isFile()){
            LOG.info("文件夹不存在，或者不是文件夹");
            return Boolean.FALSE;
        }
        try (
                // 创建zip文件流
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dir.getParent()+File.separator+dir.getName()+".zip")))){
            File[] files = dir.listFiles();
            for (File file:files) {
                compress(file,zos,file.getName());
            }
            return Boolean.TRUE;
        }catch (Exception e){
            LOG.error("压缩失败.....",e);
        }
        return Boolean.FALSE;
    }

    /**
     * 要解压的文件,要解压到的地方
     * @param srcPath
     * @param destDirPath
     * @return
     */
    public static boolean unZip(String srcPath, String destDirPath){
        File srcFie = new File(srcPath);
        if (!srcFie.exists()){
            LOG.info("文件不存在....路径为:{}",srcPath);
            return Boolean.FALSE;
        }
        try {
            ZipFile zipFile = new ZipFile(srcFie);
            zipFile.entries();
            Enumeration<?> enumerations = zipFile.entries();
            while (enumerations.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enumerations.nextElement();
                System.out.println("zipEntry.getName() = " + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    // 创建文件夹
                    String path = destDirPath + File.separator + zipEntry.getName();
                    File dir = new File(path);
                    dir.mkdirs();
                } else {
                    // 创建文件并复制其中内容
                    InputStream in = zipFile.getInputStream(zipEntry);
                    File outFile = new File(destDirPath + File.separator + zipEntry.getName());
                    if (!outFile.exists()){
                        outFile.getParentFile().mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(outFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len=in.read(buf))!=-1){
                        out.write(buf,0,len);
                    }
                    out.close();
                    in.close();
                }
            }
            return Boolean.TRUE;
        }catch (Exception e){
            LOG.error("解压失败.....",e);
        }
        return Boolean.FALSE;
    }

    /**
     * 递归处理文件夹中的文件
     * @param sourceFile
     * @param zos
     * @throws Exception
     */
    private static void compress(File sourceFile,ZipOutputStream zos,String name) throws Exception{
        if (sourceFile.isDirectory()){
            File[] listFiles = sourceFile.listFiles();
            // 空目录处理
            if(listFiles == null || listFiles.length == 0){
                // 空文件夹的处理
                zos.putNextEntry(new ZipEntry(name + "/"));
                // 没有文件，不需要文件的copy
                zos.closeEntry();
            }else {
                for (File file:listFiles) {
                    // 该子文件所在的相对路径名
                    String childName = name + File.separator+file.getName();
                    compress(file,zos,childName);
                }
            }
        }else {
            // 添加进去,会根据name创建相应目录或者文件名，一个zos流中不能使用多个重复的name
            zos.putNextEntry(new ZipEntry(name));
            int len = -1;
            byte[] buf = new byte[BUFFER_SIZE];
            FileInputStream fi = new FileInputStream(sourceFile);
            while ((len=fi.read(buf))!=-1){
                zos.write(buf,0,len);
            }
            // Complete the entry
            zos.closeEntry();
            fi.close();
        }
    }

}
