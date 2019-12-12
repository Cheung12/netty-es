package com.tz;/**
 * @Author: zz
 * @Date: 2019-12-11  10:11
 * @Description:
 **/

import com.tz.server.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
        ApplicationContext ctx= new AnnotationConfigApplicationContext(StartServer.class);
        System.out.println("Spring容器启动");
        String[] beans=ctx.getBeanDefinitionNames();
        System.out.println("已加载的组件:");
        for(String name:beans){
            System.out.println(name);
        }

        HttpServer server= (HttpServer) ctx.getBean("httpServer");
        server.start();
    }
    @Bean
    public HttpServer httpServer(){
        return new HttpServer(8088);
    }
}
