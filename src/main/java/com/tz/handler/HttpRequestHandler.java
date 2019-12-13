package com.tz.handler;/**
 * @Author: zz
 * @Date: 2019-12-11  10:02
 * @Description:
 **/

import com.alibaba.fastjson.JSONObject;
import com.tz.dto.PointDto;
import com.tz.dto.TrackDto;
import com.tz.parse.RequestParser;
import com.tz.service.ESService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@ChannelHandler.Sharable
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
        /**
         * 100 Continue
         * HTTP客户端程序有一个实体的主体部分要发送给服务器，
         * 但希望在发送之前查看下服务器是否会接受这个实体，
         * 所以在发送实体之前先发送了一个携带100
         * Continue的Expect请求首部的请求。
         * 服务器在收到这样的请求后，应该用 100 Continue或一条错误码来进行响应。
         */
        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }

        String msg ="";
        // 获取请求的uri
        String uriStr = req.uri();
        String[] uri=uriStr.split("\\?");
        req.method();
        log.info("服务器接收到请求------------------------------"+req.method());
        if(uri[0].equals("/uploadPoint")){
            ByteBuf jsonBuf = req.content();
            String jsonStr = jsonBuf.toString(CharsetUtil.UTF_8);
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            log.info("upload Point param Body--------------------:"+jsonObj);
            PointDto point=new PointDto();
            point.setId(jsonObj.getLong("id"));
            point.setLnglat(jsonObj.getString("lngLat"));
            point.setDirection(jsonObj.getInteger("direction"));
            point.setSpeed(jsonObj.getInteger("speed"));
            point.setLocationTime(jsonObj.getDate("location_time"));
            point.setTerminalId(jsonObj.getString("terminalId"));
            point.setTrackId(jsonObj.getString("trackId"));
            esService.addPoint(point);
        }else if(uri[0].equals("/uploadTrack")){
            ByteBuf jsonBuf = req.content();
            String jsonStr = jsonBuf.toString(CharsetUtil.UTF_8);
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            log.info("upload Track param Body--------------------:"+jsonObj);
            TrackDto track=new TrackDto();
            track.setTerminalId(jsonObj.getString("terminalId"));
            track.setTrackId(jsonObj.getString("trackId"));
            track.setUploadTime(jsonObj.getTimestamp("upload_time"));
            track.setDuration(jsonObj.getLong("duration"));
            track.setMileage(jsonObj.getLong("mileage"));
            esService.addTrack(track);
        }else if(uri[0].equals("/getTrack")){
            String[] element=uri[1].split("=");
            log.info("getTrack Param------------:"+uri[1]);
            esService.getTrack(element[1]);
        }else{

        }

//        Map<String,String> resMap = new HashMap<>();
//        resMap.put("method",req.method().name());
//        resMap.put("uri",uri[0]);


//        msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"<br>" +
//                "请求类型："+req.method()+"<br> "+
//                "请求headers:"+req.headers()+"<br></body></html>";

        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }



}
