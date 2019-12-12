package com.tz.service.impl;/**
 * @Author: zz
 * @Date: 2019-12-11  15:28
 * @Description:
 **/

import com.alibaba.fastjson.JSON;
import com.tz.service.ESService;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description : 
 * @FileName： ESServiceImpl.java
 * @Author :    zz
 * @Date :      2019/12/11
 **/
@Service
public class ESServiceImpl implements ESService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public void addTest()  {

    }
}
