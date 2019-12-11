package com.tz.dto;

import java.io.Serializable;
import java.util.Date;

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


    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public Date getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Date locationTime) {
        this.locationTime = locationTime;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

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
