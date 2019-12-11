package com.tz;/**
 * @Author: zz
 * @Date: 2019-12-11  10:11
 * @Description:
 **/

import com.tz.server.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description : 启动服务器
 * @FileName： StartServer.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@ComponentScan("com.tz")
@Configuration
public class StartServer {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(StartServer.class);
        HttpServer server= (HttpServer) ctx.getBean("httpServer");
        server.start();
    }

    @Bean
    public HttpServer httpServer(){
        return new HttpServer(8088);
    }
}
