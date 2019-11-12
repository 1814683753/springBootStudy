package com.le.mybatis_plus;

import com.le.mybatis_plus.entity.User;
import com.le.mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapper() {
        // 传入null,查询出所有数据
        List<User> userList = userMapper.selectList(null);
        // 判断结果是否和预期一致
        Assert.assertEquals(5, userList.size());
        // 遍历输出
        userList.forEach(System.out::println);
    }

}
