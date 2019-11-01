package com.hjl;

import com.hjl.entity.ExcelDataDemoPojo;
import com.hjl.utils.EasyExcelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：hjl
 * @date ：2019/11/1 15:39
 * @description： easyExcel测试类
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelTest {
    @Test
    public void testWrite(){
        List<ExcelDataDemoPojo> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            ExcelDataDemoPojo pojo = new ExcelDataDemoPojo();
            pojo.setDate(new Date());
            pojo.setString("test"+i);
            pojo.setDoubleData(Double.valueOf(i));
            list.add(pojo);
        }
        boolean flag = EasyExcelUtils.writeExcel(ExcelDataDemoPojo.class,"D:\\test\\test.xlsx",list,"测试");
        System.out.println("flag = " + flag);
    }
    @Test
    public void testRead(){
        List<ExcelDataDemoPojo> list = EasyExcelUtils.readExcel(ExcelDataDemoPojo.class,"D:\\test\\test.xlsx");
        list.forEach(excelDataDemoPojo -> {
            System.out.println(excelDataDemoPojo);
        });
    }
}
