package com.hjl.comman.demo;

import com.google.common.eventbus.Subscribe;

/**
 * @author ：hjl
 * @date ：2019/9/27 16:05
 * @description： 观察者1
 * @modified By：
 */
public class DataObserverDemo1 {
    @Subscribe
    public void test(String msg){
        System.out.println("String msg............." + msg);
    }
}
