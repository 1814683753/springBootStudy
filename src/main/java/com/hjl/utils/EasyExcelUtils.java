package com.hjl.utils;

import com.alibaba.excel.EasyExcel;
import com.hjl.listener.ExcelDemoDataListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/5 17:04
 * @description： 使用easy excel操作excel的工具类
 * @modified By：
 */
public final class EasyExcelUtils {

    public static final Logger LOG = LoggerFactory.getLogger(EasyExcelUtils.class);

    /**
     * 读取excel并将数据存放到指定类型对象集合中
     * @param tClass 要存放数据的对象
     * @param path excel的路径
     * @param <T> 泛型，存放数据对象
     * @param sheetName 要读取的sheet页名称 为空默认读取第一个sheet
     * @return 返回数据集合
     */
    public static <T>List<T> readExcel(Class<T> tClass,String path,String sheetName){
        List<T> dataList = new ArrayList<>();
        if (StringUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        LOG.info("start.....{}",file.getAbsolutePath());
        if (StringUtils.isEmpty(sheetName)){
            EasyExcel.read(file,tClass,new ExcelDemoDataListener<T>(dataList)).sheet().doRead();
        }else {
            EasyExcel.read(file,tClass,new ExcelDemoDataListener<T>(dataList)).sheet(sheetName).doRead();
        }

        return dataList;
    }

    /**
     * 将指定对象集合中的数据写入到excel中
     * @param tClass 要被解析的对象
     * @param path 文件存放路径
     * @param dataList 要存放的数据
     * @param sheetName sheet 标签名字
     * @param <T> 泛型
     * @return 是否写入成功
     */
    public static <T>boolean writeExcel(Class<T> tClass,String path,List<T> dataList,String sheetName){
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(sheetName)){
            LOG.info("路径或者sheetName为空");
            return Boolean.FALSE;
        }
        try {
            File file = new File(path);
            EasyExcel.write(file, tClass).sheet(sheetName).doWrite(dataList);
            return Boolean.TRUE;
        }catch (Exception e){
            LOG.error("write excel error : ",e);
        }
        return Boolean.FALSE;
    }

}
