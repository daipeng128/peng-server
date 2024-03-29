package com.peng.itrat.core.utils;

import com.peng.itrat.core.model.Page;

public class PageUtil {
    private static ThreadLocal<Page> pageLocal = new ThreadLocal();

    public PageUtil() {
    }

    public static void setPage(Page page) {
        pageLocal.set(page);
    }

    public static Page getPage() {
        return (Page)pageLocal.get();
    }
}
