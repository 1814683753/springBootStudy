package com.hjl.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.hjl.entity.ExcelDataDemoPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/10/5 21:00
 * @description： 读取excel文件的监听器
 * @modified By：
 */
public class ExcelDemoDataListener<T> extends AnalysisEventListener<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelDemoDataListener.class);

    private List<T> dataList;

    public ExcelDemoDataListener() {
        dataList = new ArrayList<>();
    }

    public ExcelDemoDataListener(List<T> dataList) {
        this.dataList = dataList;
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        LOG.info("解析到一条数据.....{}", JSON.toJSONString(t));
        dataList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOG.info("解析完成............");
    }
}
