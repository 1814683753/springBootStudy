package com.hjl.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ：hjl
 * @date ：2019/10/5 18:48
 * @description： 保存
 * @modified By：
 */
@Data
public class ExcelDataDemoPojo {
    @ExcelProperty("姓名")
    private String string;
    @ExcelProperty("日期")
    private Date date;
    @ExcelProperty("工资")
    private Double doubleData;
}
