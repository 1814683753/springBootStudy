package com.hjl.comman.demo;

import com.google.common.eventbus.Subscribe;

/**
 * @author ：hjl
 * @date ：2019/9/27 16:06
 * @description： 观察者2
 * @modified By：
 */
public class DataObserverDemo2 {

    @Subscribe
    public void test2(Integer msg){
        System.out.println("Integer msg............." + msg);
    }
    @Subscribe
    public void test3(String msg){
        System.out.println("String msg2............." + msg);
    }
}
