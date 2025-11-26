package com.szbf.constant;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatStatusEnum.java
 * @Description TODO
 * @createTime 2022年07月21日 10:48:00
 */

public enum HeartBeatStatusEnum {
    Normal("Normal",0),
    DriverAbnormal("DriverAbnormal",1),
    DriverOvertime("DriverOvertime",2),
    EquipmentAbnormal("EquipmentAbnormal",3),
    EquipmentError("EquipmentError",4),
    EquipmentOverTime("EquipmentOverTime",5),
    Monitoring("Monitoring",6);
    private String Status;
    private int Code;

    HeartBeatStatusEnum(String status, int code) {
        Status = status;
        Code = code;
    }

    HeartBeatStatusEnum() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }
}
