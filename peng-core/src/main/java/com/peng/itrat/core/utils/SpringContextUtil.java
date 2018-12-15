package com.peng.itrat.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

//    public static <T> T getBean(Class<T> clazz) {
//        checkApplicationContext();
//        return applicationContext.getBeansOfType(clazz);
//    }

    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if(applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}