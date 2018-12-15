package com.peng.itrat.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface JeesnsInterceptor {
    boolean interceptor(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception;
}
