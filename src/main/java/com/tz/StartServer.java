package com.tz;/**
 * @Author: zz
 * @Date: 2019-12-11  10:11
 * @Description:
 **/

import com.tz.server.HttpServer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * @Description : 启动服务器
 * @FileName： StartServer.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@ComponentScan("com.tz")
@PropertySource("classpath:/log4j2.properties")
@Configuration
public class StartServer {
    private static final Logger logger = LogManager.getLogger(StartServer.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx= new AnnotationConfigApplicationContext(StartServer.class);
        BasicConfigurator.configure();
        logger.info("Spring容器启动");
        String[] beans=ctx.getBeanDefinitionNames();
        logger.info("已加载的组件:");
        for(String name:beans){
            logger.info(name);
        }

        HttpServer server= (HttpServer) ctx.getBean("httpServer");
        server.start();
    }
    @Bean
    public HttpServer httpServer(){
        return new HttpServer(8088);
    }
}
