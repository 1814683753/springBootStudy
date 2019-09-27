package com.hjl.comman.demo;

/**
 * @author ：hjl
 * @date ：2019/9/27 16:07
 * @description： 测试
 * @modified By：
 */
public class EventBusTest {

    public static void main(String[] args) {
        DataObserverDemo1 demo1 = new DataObserverDemo1();
        DataObserverDemo2 demo2 = new DataObserverDemo2();
        EventBusCenter.register(demo1);
        EventBusCenter.register(demo2);
        // 调用post对象会根据传递的参数类型去调用注册对象中使用Subscribe修饰的相应方法
        EventBusCenter.post(1);
        EventBusCenter.post("test");
        EventBusCenter.post('a');
    }
}
