package com.szbf.tlstest;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName SSLServer.java
 * @Description TODO
 * @createTime 2025年07月25日 14:46:00
 */
public class SSLServer {
    private static final int PORT = 8443;

    public static void main(String[] args) throws Exception {
        SSLContext sslContext = TLSUtils.createSSLContext(
                "C:\\Program Files\\Java\\jdk1.8.0_151\\bin\\server.controller", "123456",
                "C:\\Program Files\\Java\\jdk1.8.0_151\\bin\\server.controller","123456");

        SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(PORT);
        serverSocket.setNeedClientAuth(true); // 强制客户端认证

        System.out.println("TLS 1.3服务器启动，等待连接...");
        while (true) {
            try (SSLSocket socket = (SSLSocket) serverSocket.accept()) {
                System.out.println("客户端连接成功：" +
                        socket.getSession().getPeerCertificates()[0]);
                // 业务处理...
            }
        }
    }
}

