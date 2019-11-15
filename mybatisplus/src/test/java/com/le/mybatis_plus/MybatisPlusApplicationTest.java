package com.le.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.le.mybatis_plus.entity.User;
import com.le.mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
class MybatisPlusApplicationTest {

    private static final Logger LOG = LoggerFactory.getLogger(MybatisPlusApplicationTest.class);
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

    /**
     * 简单的crud测试
     */
    @Test
    void testCrud(){
        // 插入
        User user = new User();
        user.setName("test");
        user.setAge(20);
        user.setEmail("test@11.com");
        int count = userMapper.insert(user);
        LOG.info("count : {}",count);
        user = userMapper.selectById(1);
        LOG.info("user is : {}",user);
        //查询
        List<User> list = userMapper.selectList(null);
        list.forEach(u -> {
            // 修改
            LOG.info("user:{}",u);
            u.setName(u.getName() + "_u");
            userMapper.updateById(u);
        });
        //删除
        userMapper.deleteById(6);
        list = userMapper.selectList(null);
        list.forEach(u -> LOG.info("user:{}",u));
    }
    @Test
    void queryByQueryWrapper(){
        // 创建查询wrapper构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置条件为id=1 and age is not null
        queryWrapper.eq("id",1).isNotNull("age");
        User user = userMapper.selectOne(queryWrapper);
        LOG.info("user : {}",user);
    }
    @Test
    void queryList(){
        User user = new User();
        user.setAge(20);
        // 创建查询wrapper构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper = new QueryWrapper<>();
        /**
         * 条件构造器中这些方法中出现的第一个入参boolean condition表示该条件是否加入最后生成的sql中,不传默认是true
         * 该参数可以用于校验要使用的参数是否付和规范，符合就加入到sql中
         */
        queryWrapper.eq(Objects.nonNull(user),"age",user.getAge()).isNotNull("age");
        // 如果传入null则查询所有
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
        userList.clear();
        // 除了使用构造器外还可以使用map
        LOG.info("===========map============");
        // map条件查询,key为列名，value为具体的值
        Map<String,Object> map = new HashMap<>();
        map.put("age",20);
        // 如果传入的参数为null则查询所有
        userList = userMapper.selectByMap(map);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
        userList.clear();
        LOG.info("===========lambdaQuery============");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAge,20).isNotNull(User::getName);
        // 如果传入null则查询所有
        userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
    }
    @Test
    void queryTest(){
        // 创建查询wrapper构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1).isNotNull("age");
        User user = userMapper.selectOne(queryWrapper);
        LOG.info("user : {}",user);
        LOG.info("===========map============");
        // map条件查询,key为列名，value为具体的值
        Map<String,Object> map = new HashMap<>();
        map.put("age",20);
        // 如果传入的参数为null则查询所有
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
        userList.clear();
        LOG.info("=========queryWrapper=====");
        // 通过条件构造器获取数据集合，相比较与
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",20).isNotNull("age");
        // 如果传入null则查询所有
        userList = userMapper.selectList(queryWrapper);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
        userList.clear();
        LOG.info("===========lambdaQuery===========");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAge,20).isNotNull(User::getName);
        // 如果传入null则查询所有
        userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
    }
    @Test
    void testInsert(){
        User user = new User();
        user.setName("test_8");
        user.setAge(19);
        user.setEmail("test@a.com");
        int count = userMapper.insert(user);
        LOG.info("插入成功：{}条",count);
    }
    @Test
    void testDelete(){
        // 使用条件构造器删除符合条件的,使用QueryWrapper和UpdateWrapper都行，没有删除相关的wrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        int count = userMapper.delete(queryWrapper.eq("name","test_8"));
        LOG.info("删除成功：{}条",count);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        count = userMapper.delete(updateWrapper.eq("name","Jone_u"));
        LOG.info("删除成功：{}条",count);
        // 使用map删除
        Map<String,Object> map = new HashMap<>();
        map.put("id",7);
        count = userMapper.deleteByMap(map);
        LOG.info("删除成功：{}条",count);
        // 使用id删除
        count = userMapper.deleteById(5);
        LOG.info("删除成功：{}条",count);
    }
    @Test
    void testUpdate(){
        // 创建相应update构造器
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        // 设置条件将所有年龄为21时将年龄设置成22
        userUpdateWrapper.eq("age",22).set("age",20);
        // 根据构造器修改符合条件的数据
        int count = userMapper.update(new User(),userUpdateWrapper);
        LOG.info("count : {}",count);
        List<User> userList = userMapper.selectByMap(null);
        userList.forEach(u ->{
            LOG.info("user : {}",u);
        });
        LOG.info("=============update by id==========");
        // 获取要修改的对象
        User user = userMapper.selectById(1);
        LOG.info("update before user : {}",user);
        user.setName("test");
        // 根据传入对象的id去修改指定的记录
        count = userMapper.updateById(user);
        LOG.info("count : {}",count);
        user = userMapper.selectById(1);
        LOG.info("updateById after user : {}",user);
    }
    @Test
    void testPagination(){
        Page<User> page = new Page<>();
        // 设置当前是哪一页，页数从1开始,传入小于1的数据都会当作1处理
        page.setCurrent(-1);
        // 设置每页的数据大小
        page.setSize(2);
        IPage<User> userIPage = userMapper.selectPage(page,null);
        // 获取数据库中总记录数
        LOG.info("total : {}",userIPage.getTotal());
        // 打印当前页中所有记录
        userIPage.getRecords().forEach(user -> {
            LOG.info("user : {}",user);
        });
    }



}
