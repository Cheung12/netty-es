package com.tz.dto.enums;

public enum  TerminalStateEnum {

    STOP_TAKE("0","停止接单"),
    TAKING("1","听单中"),
    SERVING("2","服务中");

    /**
     * 代码
     */
    private String code;

    /**
     * 描述
     */
    private String des;


    TerminalStateEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "VehicleStateEnum{" +
                "code=" + code +
                ", des='" + des + '\'' +
                "} " + super.toString();
    }

}
