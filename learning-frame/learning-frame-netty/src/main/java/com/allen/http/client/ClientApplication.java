package com.allen.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class ClientApplication {

    private int port;
    private String host;

    public ClientApplication(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        try {
            new ClientApplication("127.0.0.1", 10008).start();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    socketChannel.pipeline().addLast(new ClientSocketChannel());
                                }
                            });
            ChannelFuture sync = b.connect().sync();
            sync.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    @Slf4j
    public static class ClientSocketChannel extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
            log.info("收到服务端信息：" + in.toString(CharsetUtil.UTF_8));
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            log.error("", cause);
            ctx.close();
        }
    }
}
