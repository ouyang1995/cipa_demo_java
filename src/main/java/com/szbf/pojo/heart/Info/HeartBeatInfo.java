package com.szbf.pojo.heart.Info;

import com.szbf.constant.HeartBeatStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName HeartBeatInfo.java
 * @Description TODO
 * @createTime 2022年07月21日 10:47:00
 */
@Data
public class HeartBeatInfo {
    private int heartBeatStatus;
    private Date heartBeatTime;
}
