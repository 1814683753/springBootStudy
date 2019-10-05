package com.hjl.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.hjl.entity.ExcelDataDemoPojo;
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

    public static <T>List<T> readExcel(Class<T> tClass,String path){
        List<T> dataList = new ArrayList<>();
        if (StringUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        LOG.info("start.....{}",file.getAbsolutePath());
        EasyExcel.read(file,tClass,new ExcelDemoDataListener<T>(dataList)).sheet().doRead();
        return dataList;
    }

    public static void main(String[] args) {
        List<ExcelDataDemoPojo> list = EasyExcelUtils.readExcel(ExcelDataDemoPojo.class,"C:\\Users\\18146\\Desktop\\test.xlsx");
        list.forEach(demo -> System.out.println(demo));
    }
}
