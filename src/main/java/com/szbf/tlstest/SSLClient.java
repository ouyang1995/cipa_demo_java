package com.szbf.tlstest;

import com.szbf.pojo.method.RegisterInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.*;

public class SSLClient {
    public static void main(String[] args) throws Exception {
        SSLContext sslContext = TLSUtils.createSSLContext(
                "C:\\Program Files\\Java\\jdk1.8.0_151\\bin\\server.controller", "123456",
                "C:\\Program Files\\Java\\jdk1.8.0_151\\bin\\server.controller","123456");



        SSLSocketFactory sf = sslContext.getSocketFactory();
        try (SSLSocket socket = (SSLSocket) sf.createSocket("127.0.0.1", 12398)) {
            socket.startHandshake();
            System.out.println("成功连接到服务器，服务端证书：" +
                    socket.getSession().getPeerCertificates()[0]);

        }
    }
}
