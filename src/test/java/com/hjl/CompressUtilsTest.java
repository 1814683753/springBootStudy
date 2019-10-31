package com.hjl;

import com.hjl.utils.CompressUtils;
import org.junit.Test;

/**
 * @author ：hjl
 * @date ：2019/10/31 15:24
 * @description： 解压缩工具测试
 * @modified By：
 */
public class CompressUtilsTest {

    @Test
    public void testUnZip() {
        boolean isSuccess = CompressUtils.unZip("D:\\document\\work\\document\\2019-10-31.zip","D:\\document\\work\\document\\test");
        System.out.println("isSuccess = " + isSuccess);
    }

    @Test
    public void testDirZip() {
        boolean isSuccess = CompressUtils.dirToZip("D:\\document\\work\\document\\2019-10-31");
        System.out.println("isSuccess = " + isSuccess);
    }
}
