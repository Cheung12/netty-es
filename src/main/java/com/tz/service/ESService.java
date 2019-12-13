package com.tz.service;

import com.tz.dto.PointDto;
import com.tz.dto.TrackDto;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

/**
 * @Author: zz
 * @Date: 2019-12-11  15:27
 * @Description:
 **/
public interface ESService {
    /**
     * @description 上报坐标
     * @date  2019/12/13
     * @param []
     * @return void
     **/
    IndexResponse addPoint(PointDto point) throws IOException;

    IndexResponse addTrack(TrackDto track) throws IOException;

    void getTrack(String trackId) throws IOException;



    /*
     * @description 删除一个坐标
     * @date  2019/12/13
     * @param []
     * @return void
     **/
    void deletePoint(String id) throws IOException;

    void deleteTrack(String trackId) throws IOException;
}
