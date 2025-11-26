package com.szbf.pojo.heart;

import lombok.Data;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName health.java
 * @Description TODO
 * @createTime 2022年07月21日 15:10:00
 */

public class health {
    private String status;

    private details details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public com.szbf.pojo.heart.details getDetails() {
        return details;
    }

    public void setDetails(com.szbf.pojo.heart.details details) {
        this.details = details;
    }

    public health(String status, com.szbf.pojo.heart.details details) {
        this.status = status;
        this.details = details;
    }

    public health() {
    }
}
