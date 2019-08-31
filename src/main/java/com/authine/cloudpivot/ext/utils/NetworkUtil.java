package com.authine.cloudpivot.ext.utils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

public final class NetworkUtil {


    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        // X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0
                || "unknown".equalsIgnoreCase(ipAddresses)) {
            // Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0
                || "unknown".equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0
                || "unknown".equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0
                || "unknown".equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0
                || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *
     * @Title: getServerIpAndPort
     * @Description:获取应用的IP地址和端口
     * @return 127.0.0.1:8080
     *
     */
    public static String getServerIpAndPort() {
        String listenAddr = null;
        String port = null;
        try {
            Context ctx = new InitialContext();
            MBeanServer tMBeanServer = (MBeanServer) ctx
                    .lookup("java:comp/env/jmx/runtime");
            ObjectName tObjectName = new ObjectName(
                    "com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
            ObjectName serverrt = (ObjectName) tMBeanServer.getAttribute(
                    tObjectName, "ServerRuntime");
            port = String.valueOf(tMBeanServer.getAttribute(serverrt,
                    "ListenPort"));
            listenAddr = (String) tMBeanServer.getAttribute(serverrt,
                    "ListenAddress");
            String[] tempAddr = listenAddr.split("/");
            if (tempAddr.length == 1) {
                listenAddr = tempAddr[0];
            } else if (tempAddr[tempAddr.length - 1].trim().length() != 0) {
                listenAddr = tempAddr[tempAddr.length - 1];
            } else if (tempAddr.length > 2) {
                listenAddr = tempAddr[tempAddr.length - 2];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 防止为空导致后续请求失败
        if (listenAddr.isEmpty()) {
            listenAddr = "127.0.0.1";
        }
        if (port.isEmpty()) {
            port = "8080";
        }
        return listenAddr + ":" + port;
    }
}