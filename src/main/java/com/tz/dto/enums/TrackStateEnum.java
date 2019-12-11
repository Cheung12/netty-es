package com.tz.dto.enums;

import java.io.Serializable;

/**
 * 轨迹状态
 */
public enum  TrackStateEnum implements Serializable {
    INIT("1","未派单"),
    DISPATHED("2","已派单"),
    PRE_STARTED("201","到达上车点"),
    STARTED("3","开始计费"),
    REVOKED("4","订单撤销"),
    END("6","结束行程");

    /**
     * 代码
     */
    private String code;

    /**
     * 描述
     */
    private String des;


    TrackStateEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
