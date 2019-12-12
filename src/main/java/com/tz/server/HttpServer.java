package com.tz.server;/**
 * @Author: zz
 * @Date: 2019-12-11  9:56
 * @Description:
 **/


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @Description : http 服务器
 * @FileName： HttpServer.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@Component
public class HttpServer {
    private static final Logger logger = LogManager.getLogger(HttpServer.class);

    @Autowired
    private HttpServerInitializer httpServerInitializer;


    int port ;

    public HttpServer(int port){
        this.port = port;
    }

    public void start() throws Exception{
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss,work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(httpServerInitializer);

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        logger.info("------- netty server started up on port : " + port);
        f.channel().closeFuture().sync();

    }
}
