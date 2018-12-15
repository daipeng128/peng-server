package com.peng.itrat.core.utils;

import java.util.UUID;

public class UuidUtil {
    public UuidUtil() {
    }

    public static String getUnid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
