package com.peng.itrat.core.utils;

import com.peng.itrat.core.enums.Messages;
import com.peng.itrat.core.exception.ParamException;

public class ValidUtill {
    public ValidUtill() {
    }

    public static void checkParam(Boolean boo, String msg) {
        if(boo.booleanValue()) {
            throw new ParamException(msg);
        }
    }

    public static void checkParam(Boolean... boos) {
        Boolean[] var1 = boos;
        int var2 = boos.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            boolean boo = var1[var3].booleanValue();
            if(boo) {
                throw new ParamException();
            }
        }

    }

    public static void checkIsNull(Object obj) {
        if(null == obj) {
            throw new ParamException();
        }
    }

    public static void checkIsNull(Object obj, String msg) {
        if(null == obj) {
            throw new ParamException(msg);
        }
    }

    public static void checkIsBlank(String val, String msg) {
        if(StringUtils.isBlank(val)) {
            throw new ParamException(msg);
        }
    }

    public static void checkIsNull(Object obj, Messages messages) {
        if(null == obj) {
            throw new ParamException(messages);
        }
    }

    public static void checkIsBlank(String val, Messages messages) {
        if(StringUtils.isBlank(val)) {
            throw new ParamException(messages);
        }
    }
}
