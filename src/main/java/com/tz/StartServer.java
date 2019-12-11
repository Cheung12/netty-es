package com.tz;/**
 * @Author: zz
 * @Date: 2019-12-11  10:11
 * @Description:
 **/

import com.tz.server.HttpServer;

/**
 * @Description : 启动服务器
 * @FileName： StartServer.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
public class StartServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer(8088);
        server.start();
    }
}
