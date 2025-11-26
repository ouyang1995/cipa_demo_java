package com.szbf.pojo.heart;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName diskSpace.java
 * @Description TODO
 * @createTime 2022年07月21日 15:13:00
 */

public class diskSpace {
    private String status;
    private diskSpaceDetails details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public diskSpaceDetails getDetails() {
        return details;
    }

    public void setDetails(diskSpaceDetails details) {
        this.details = details;
    }

    public diskSpace() {
    }

    public diskSpace(String status, diskSpaceDetails details) {
        this.status = status;
        this.details = details;
    }
}
