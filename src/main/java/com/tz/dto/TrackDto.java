package com.tz.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TrackDto implements Serializable {

    /**
     * 设备id
     */
    private String terminalId;
    /**
     * 轨迹ID
     */
    private String trackId;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 轨迹点 最多100个
     */
    private List<PointDto>  points;
    /**
     * 里程 单位米
     */
    private Long mileage;
    /**
     * 时长 单位米
     */
    private Long duration;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<PointDto> getPoints() {
        return points;
    }

    public void setPoints(List<PointDto> points) {
        this.points = points;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TrackDto{" +
                "terminalId='" + terminalId + '\'' +
                ", trackId='" + trackId + '\'' +
                ", uploadTime=" + uploadTime +
                ", points=" + points +
                ", mileage=" + mileage +
                ", duration=" + duration +
                '}';
    }
}
