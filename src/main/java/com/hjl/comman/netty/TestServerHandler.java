package com.hjl.comman.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ：hjl
 * @date ：2019/12/17 17:24
 * @description：
 * @modified By：
 */
public class TestServerHandler extends SimpleChannelInboundHandler<StudentInfo.Student> {// 泛型类型Student


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentInfo.Student msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
