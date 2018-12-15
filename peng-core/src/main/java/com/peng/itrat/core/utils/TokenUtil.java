package com.peng.itrat.core.utils;

import java.util.UUID;

public class TokenUtil {
    public TokenUtil() {
    }

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
