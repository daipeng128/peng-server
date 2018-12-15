package com.peng.itrat.core.utils;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class LocaleUtil {
    private static final ThreadLocal<Locale> tlLocale = new ThreadLocal() {
        protected Locale initialValue() {
            return Locale.SIMPLIFIED_CHINESE;
        }
    };
    private static MessageSource messageSource;

    public LocaleUtil() {
    }

    public static Locale getLocale() {
        return (Locale)tlLocale.get();
    }

    public static void setMessageSource(MessageSource messageSource) {
        messageSource = messageSource;
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }
}
