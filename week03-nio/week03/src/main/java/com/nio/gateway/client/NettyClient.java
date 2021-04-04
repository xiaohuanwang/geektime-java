package com.nio.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @description: netty Client
 * @author wangxiaohuan
 * @Date 9:39 下午 2021/4/3
   No such property: code for class: Script1
 * @return
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new HttpInboundHandler(new Date() + ": helloWorld---"));
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8888).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello World");
            Thread.sleep(3000);
        }
    }

    public static class HttpInboundHandler extends ChannelInboundHandlerAdapter {

        private String content;

        public HttpInboundHandler(String content) {
            this.content = content;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.channel().writeAndFlush(content);
            super.channelActive(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
        }
    }
}
