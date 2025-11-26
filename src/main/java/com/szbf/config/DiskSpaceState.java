package com.szbf.config;

import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.util.unit.DataSize;

import java.io.File;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName DiskSpaceState.java
 * @Description TODO
 * @createTime 2022年07月21日 16:56:00
 */
public class DiskSpaceState extends DiskSpaceHealthIndicator{

    public DiskSpaceState(File path, DataSize threshold) {
        super(path, threshold);
    }
}
