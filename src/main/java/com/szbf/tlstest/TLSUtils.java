package com.szbf.tlstest;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;

public class TLSUtils {
    public static SSLContext createSSLContext(String keystorePath,
                                              String keystorePass, String truststorePath, String truststorePass)
            throws Exception {
        // 初始化密钥管理器
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keystorePath), keystorePass.toCharArray());
        kmf.init(keyStore, keystorePass.toCharArray());

        // 初始化信任管理器
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new FileInputStream(truststorePath), truststorePass.toCharArray());
        tmf.init(trustStore);

        // 创建TLS 1.3上下文
        SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext;
    }


    public static SSLContext createSSLContextTest(String keystorePath,
                                              String keystorePass)
            throws Exception {
        // 初始化密钥管理器
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keystorePath), keystorePass.toCharArray());
        kmf.init(keyStore, keystorePass.toCharArray());



        // 创建TLS 1.3上下文
        SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
        sslContext.init(kmf.getKeyManagers(), null, null);
        return sslContext;
    }
}