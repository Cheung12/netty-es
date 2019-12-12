package com.tz.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;
@Data
public class PointDto implements Serializable {

    /**
     * 经纬度
     */
    private String lnglat;

    /**
     * 订单时间
     */
    private Date locationTime;

    /**
     * 当前速度米/s
     */
    private Integer speed;

    /**
     * 定位方向 0-360
     */
    private Integer direction;




    @Override
    public String toString() {
        return "PointDto{" +
                "lnglat='" + lnglat + '\'' +
                ", locationTime=" + locationTime +
                ", speed=" + speed +
                ", direction=" + direction +
                '}';
    }
}
