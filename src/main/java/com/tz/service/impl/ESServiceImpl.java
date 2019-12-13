package com.tz.service.impl;/**
 * @Author: zz
 * @Date: 2019-12-11  15:28
 * @Description:
 **/

import com.tz.dto.PointDto;
import com.tz.dto.TrackDto;
import com.tz.service.ESService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
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
    public IndexResponse addPoint(PointDto point) throws IOException {
        Map<String,Object> map=new HashMap<>();
        map.put("terminal_id",point.getTerminalId());
        map.put("track_id",point.getTrackId());
        map.put("lnglat",point.getLnglat());
        map.put("location_time",point.getLocationTime());
        map.put("speed",point.getSpeed());
        map.put("direction",point.getDirection());
        IndexRequest request=new IndexRequest("point_idx").id(point.getTerminalId()).source(map);
        //request.timeout("1s");
        return client.index(request, RequestOptions.DEFAULT);
    }

    public IndexResponse addTrack(TrackDto track) throws IOException {
        Map<String,Object> map=new HashMap<>();
        map.put("track_id",track.getTerminalId());
        map.put("terminal_id",track.getTrackId());
        map.put("upload_time",track.getUploadTime());
        map.put("duration",track.getDuration());
        IndexRequest request=new IndexRequest("track_idx").id(track.getTrackId()).source(map);
        //request.timeout("1s");
        return client.index(request, RequestOptions.DEFAULT);
    }

    @Override
    public void getTrack(String trackId) throws IOException {
        //获取Track
        GetRequest trackRequest = new GetRequest(
                "track_idx",
                trackId);
        //获取点串
        GetRequest pointRequest = new GetRequest(
                "point_idx",
                trackId);
        logger.info("track---------------:"+client.get(trackRequest, RequestOptions.DEFAULT));
        logger.info("point---------------:"+client.get(pointRequest, RequestOptions.DEFAULT));
    }

    @Override
    public void deletePoint(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(
                "point_idx",
                id);
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
    }

    @Override
    public void deleteTrack(String trackId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                "point_idx",
                "1");
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
    }
}
