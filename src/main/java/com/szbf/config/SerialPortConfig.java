package com.szbf.config;


import gnu.io.SerialPortEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SerialPortConfig {

    @Bean("serialPortEventListener")
    public SerialPortEventListener serialPortEventListener(){
        return new SerialListener();
    }
}
