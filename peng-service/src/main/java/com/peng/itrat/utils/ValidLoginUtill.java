package com.peng.itrat.utils;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.enums.Messages;
import com.peng.itrat.core.exception.NotLoginException;
import com.peng.itrat.core.exception.ParamException;
import com.peng.itrat.core.utils.StringUtils;

/**
 * @author zchuanzhao@linewell.com
 * 2018/8/2
 */
public class ValidLoginUtill {

    public static void checkLogin(Member member){
        if (null == member){
            throw new NotLoginException();
        }
    }

    public static void checkParam(Boolean... boos){
        for (boolean boo :boos){
            if (!boo){
                throw new ParamException();
            }
        }
    }

    public static void checkIsNull(Object obj){
        if (null == obj){
            throw new ParamException();
        }
    }

    public static void checkIsNull(Object obj, Messages messages){
        if (null == obj){
            throw new ParamException(messages);
        }
    }
    public static void checkIsBlank(String val, Messages messages){
        if (StringUtils.isBlank(val)){
            throw new ParamException(messages);
        }
    }
}
