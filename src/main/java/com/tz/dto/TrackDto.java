package com.tz.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TrackDto implements Serializable {

    /**
     * 轨迹ID
     */

    private String trackId;
    /**
     * 设备id
     */
    private String terminalId;
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
