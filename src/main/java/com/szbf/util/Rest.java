package com.szbf.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName RestTemplate.java
 * @Description TODO
 * @createTime 2022年02月21日 14:04:00
 */
@Service
public class Rest {
    private RestTemplate restTemplate=new RestTemplate();
    @Value("${cfg.serverip}")
    private String ip;

    public void Post(Object o) throws ClassNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(o, headers);
        restTemplate.postForEntity(ip, entity, String.class);
    }
}
