package com.tz.server;/**
 * @Author: zz
 * @Date: 2019-12-11  10:02
 * @Description:
 **/

import com.tz.handler.HttpRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description : 
 * @FileName： HttpServerInitializer.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@Component
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HttpRequestHandler httpRequestHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // http 编解码
        pipeline.addLast(new HttpServerCodec());
        // http 消息聚合器   512*1024为接收的最大contentlength
        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024));

        // 请求处理器
        pipeline.addLast(httpRequestHandler);

    }
}
