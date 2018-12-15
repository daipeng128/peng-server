package com.peng.itrat.core.invoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.peng.itrat.core.annotation.Plugin;
import com.peng.itrat.core.exception.JeeException;
import com.peng.itrat.core.exception.NotAuthorizeException;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.utils.SpringContextUtil;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public class JeesnsInvoke {
    public JeesnsInvoke() {
    }

    public static Object invoke(String className, String methodName, Object... params) {
        try {
            Object e = SpringContextUtil.getBean(className);
            Class var10 = e.getClass();
            if(var10.toString().startsWith("class com.sun.proxy")) {
                e = ((SingletonTargetSource)((Advised)e).getTargetSource()).getTarget();
                var10 = e.getClass();
            }

            validate(var10);
            Class[] paramsTypes = new Class[params.length];

            for(int method = 0; method < params.length; ++method) {
                paramsTypes[method] = params[method].getClass();
            }

            Method var11 = var10.getMethod(methodName, paramsTypes);
            return var11.invoke(e, params);
        } catch (NoSuchBeanDefinitionException var7) {
            throw new NotAuthorizeException();
        } catch (NoSuchMethodException var8) {
            throw new NotAuthorizeException();
        } catch (Exception var9) {
            var9.printStackTrace();
            String msg = var9.getMessage();
            if(var9 instanceof InvocationTargetException) {
                msg = ((InvocationTargetException)var9).getTargetException().getMessage();
            }

            throw new JeeException(msg);
        }
    }

    private static void validate(Class clazz) {
        Annotation plugin = clazz.getAnnotation(Plugin.class);
        if(null == plugin) {
            throw new OpeErrorException("非法访问");
        }
    }
}
