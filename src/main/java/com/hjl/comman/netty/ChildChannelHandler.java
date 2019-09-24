package com.hjl.comman.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author hjl
 * @Description 处理类
 * @Date 2019/9/18 16:57
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private static final Logger LOG = LoggerFactory.getLogger(ChildChannelHandler.class);


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ///socketChannel.pipeline().addLast(new ServerHandler());
        socketChannel.pipeline().addLast(new HttpServerHandler());
    }
}
