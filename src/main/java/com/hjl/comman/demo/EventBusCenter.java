package com.hjl.comman.demo;

import com.google.common.eventbus.EventBus;

/**
 * @author ：hjl
 * @date ：2019/9/27 16:00
 * @description：
 * @modified By：
 */
public class EventBusCenter {
    private static EventBus eventBus = new EventBus();

    public static EventBus getInstance(){
        return eventBus;
    }

    public static void register(Object obj){
        eventBus.register(obj);
    }

    public static void unRegister(Object obj){
        eventBus.unregister(obj);
    }

    public static void post(Object obj){
        eventBus.post(obj);
    }
}
