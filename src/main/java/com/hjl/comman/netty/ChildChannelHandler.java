package com.hjl.comman.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
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
        //socketChannel.pipeline().addLast(new HttpServerHandler());
        ChannelPipeline pipeline = socketChannel.pipeline();
        //netty对protobuf提供了主要有四个handler处理器
        pipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        //ProtobufDecoder类型就是我们需要转换的类的实例，这里需要转换的就是Student
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(StudentInfo.Student.getDefaultInstance()));
        pipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast("testServerHandler", new TestServerHandler());
    }
}
