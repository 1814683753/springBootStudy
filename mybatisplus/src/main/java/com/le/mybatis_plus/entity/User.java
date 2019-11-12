package com.le.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：hjl
 * @date ：2019/11/12 10:30
 * @description：用户类
 * @modified By：
 */
// data注解将会自动为类属性提供get、set方法, 此外还提供了 equals()、hashCode()、toString() 方法
@Data
// 若表名和实体类名不一致则需使用@TableName（）来映射对应的表名
@TableName("t_user")
public class User {
    /**
     * 用户id
     */
    // 设置字段与主键对应字段映射，value为对应表中的字段名称，一样的话可以不使用注解，type = IdType.AUTO表示自动递增
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;
    /**
     * 用户姓名
     */
    // 设置属性字段与表字段映射，value为对应表中的字段名称，一样的话可以不使用注解，如果忽略对应的字段则可使用exist = false
    @TableField(value = "name" ,exist = true)
    private String name;
    /**
     * 用户年龄
     */
    @TableField(value = "age")
    private Integer age;
    /**
     * 邮件
     */
    @TableField(value = "email")
    private String email;
}
