package com.peng.itrat.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.util.HtmlUtils;

public class XssWrapper extends HttpServletRequestWrapper {
    private static final String REGEX_SCRIPT = "<script[\\s\\S]*?<\\/script>";
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";

    public XssWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if(values == null) {
            return null;
        } else {
            int count = values.length;
            String[] encodedValues = new String[count];

            for(int i = 0; i < count; ++i) {
                encodedValues[i] = this.cleanXSS(values[i]);
            }

            return encodedValues;
        }
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return value == null?null:this.cleanXSS(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        return value == null?null:this.cleanXSS(value);
    }

    private String cleanXSS(String value) {
        value = dealScript(value);
        value = dealStyle(value);
        String[] eventKeywords = new String[]{"onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick", "ondblclick", "onkeypress", "onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp", "onreadystatechange", "onrowenter", "onrowexit", "onselectstart", "onload", "onunload", "onbeforeunload", "onblur", "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu", "alert"};

        for(int i = 0; i < eventKeywords.length; ++i) {
            value = value.replaceAll("(?i)" + eventKeywords[i], "_" + eventKeywords[i]);
        }

        return value;
    }

    private static String dealScript(String val) {
        Pattern p = Pattern.compile("<script[\\s\\S]*?<\\/script>");
        return htmlEscape(p, val);
    }

    private static String dealStyle(String val) {
        Pattern p = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>");
        return htmlEscape(p, val);
    }

    private static String htmlEscape(Pattern p, String val) {
        String s;
        String newVal;
        for(Matcher m = p.matcher(val); m.find(); val = val.replace(s, newVal)) {
            s = m.group();
            newVal = HtmlUtils.htmlEscape(s);
        }

        return val;
    }
}
