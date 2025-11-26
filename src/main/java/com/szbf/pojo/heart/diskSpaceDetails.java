package com.szbf.pojo.heart;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName diskSpaceDetails.java
 * @Description TODO
 * @createTime 2022年07月21日 15:15:00
 */

public class diskSpaceDetails {
    private Long total;
    private Long free;
    private Long threshold;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFree() {
        return free;
    }

    public void setFree(Long free) {
        this.free = free;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public diskSpaceDetails() {
    }

    public diskSpaceDetails(Long total, Long free, Long threshold) {
        this.total = total;
        this.free = free;
        this.threshold = threshold;
    }
}
