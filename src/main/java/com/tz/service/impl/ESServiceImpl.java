package com.tz.service.impl;/**
 * @Author: zz
 * @Date: 2019-12-11  15:28
 * @Description:
 **/

import com.tz.dto.PointDto;
import com.tz.service.ESService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 
 * @FileName： ESServiceImpl.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@Service
public class ESServiceImpl implements ESService {
    private static final Logger logger = LogManager.getLogger(ESServiceImpl.class);



    @Autowired
    private RestHighLevelClient client;


    @Override
    public void addTest() throws IOException {

        Map<String,Object> map=new HashMap<>();
        //map.put("id",1);
        map.put("lnglat","39.915378,116.401394");
        map.put("location_time",System.currentTimeMillis());
        map.put("speed",60);
        map.put("direction",250);

        IndexRequest request=new IndexRequest("point_idx").id("1").source(map);
        //request.timeout("1s");
        IndexResponse indexResponse=client.index(request, RequestOptions.DEFAULT);
        logger.info("ES响应-----------------:"+indexResponse);
    }
}
