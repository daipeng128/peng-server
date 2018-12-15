package com.peng.itrat.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicUtil {
    public TopicUtil() {
    }

    public static String getTopicName(String content) {
        String regex = "#(\\S+)#";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        return m.find()?m.group(1):null;
    }

    public static String formatTopic(String content) {
        try {
            String e = getTopicName(content);
            if(StringUtils.isNotBlank(e)) {
                return content.replace("#" + e + "#", "<a href=\'" + Const.PROJECT_PATH + "/weibo/topic/" + URLEncoder.encode(e, "utf-8") + "\' target=\'_blank\'>#" + e + "#</a>");
            }
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
        }

        return content;
    }
}