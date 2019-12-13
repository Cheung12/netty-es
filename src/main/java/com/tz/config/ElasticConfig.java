package com.tz.config;/**
 * @Author: zz
 * @Date: 2019-12-12  9:41
 * @Description:
 **/

import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : ES配置
 * @FileName： ElasticConfig.java
 * @Author :    zz
 * @Date :      2019/12/12
 **/
@Configuration
@PropertySource("classpath:es.properties")
public class ElasticConfig {
    private static Logger log = Logger.getLogger(ElasticConfig.class);

    @Value("${elasticsearch.cluster-nodes}")
    private  String hostsStr;

    private  List<HttpHost> hostList;


    @Bean
    RestHighLevelClient client() {
        if(hostsStr != null){
            hostList = new ArrayList<>();
            String[] hosts = hostsStr.split(",");
            for (String hostPort : hosts) {
                String[] hostAndPort=hostPort.split(":");
                hostList.add(new HttpHost(hostAndPort[0], Integer.valueOf(hostAndPort[1]), "http"));
            }

            HttpHost[] httpHosts=new HttpHost[hostList.size()];
            for(int i=0;i<httpHosts.length;i++){
                httpHosts[i]=new HttpHost(hostList.get(i).getHostName(),
                        hostList.get(i).getPort(),"http");
            }
            return  new RestHighLevelClient(
                    RestClient.builder(httpHosts));
        } else {
            log.error("ES配置文件错误!");
            return null;
        }
    }



}
