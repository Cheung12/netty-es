package com.tz.handler;/**
 * @Author: zz
 * @Date: 2019-12-11  10:02
 * @Description:
 **/

import com.tz.parse.RequestParser;
import com.tz.service.ESService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;

/**
 * @Description : 业务handler
 * @FileName： HttpRequestHandler.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@Component
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static Logger log = Logger.getLogger(HttpRequestHandler.class);

    @Autowired
    private ESService esService;
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {

        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }

        Map<String, String> parmMap = new RequestParser(req).parse();
        System.out.println(parmMap);
        // 获取请求的uri
        String uri = req.uri();
        //esService.test();


        Map<String,String> resMap = new HashMap<>();
        resMap.put("method",req.method().name());
        resMap.put("uri",uri);


        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"<br>" +
                "请求类型："+req.method()+"<br> "+
                "请求headers:"+req.headers()+"<br></body></html>";

        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
