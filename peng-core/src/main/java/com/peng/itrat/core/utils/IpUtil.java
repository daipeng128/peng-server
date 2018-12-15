package com.peng.itrat.core.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class IpUtil {
    public IpUtil() {
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        } else {
            ip = request.getHeader("X-Forwarded-For");
            if(!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(44);
                return index != -1?ip.substring(0, index):ip;
            } else {
                return request.getRemoteAddr();
            }
        }
    }
}
