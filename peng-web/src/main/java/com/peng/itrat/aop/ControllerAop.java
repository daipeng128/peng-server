package com.peng.itrat.aop;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.exception.JeeException;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * @author zchuanzhao
 */
public class ControllerAop {

    public Object handlerController(ProceedingJoinPoint pjp){
        ResultModel<?> result;
        try {
            result = (ResultModel) pjp.proceed();
        } catch (Throwable e) {
            result = handlerExceotion(pjp, e);
        }
        return result;
    }

    private ResultModel<?> handlerExceotion(ProceedingJoinPoint pjp, Throwable e){
        ResultModel<?> result = new ResultModel();
        if (e instanceof JeeException && null != ((JeeException) e).getJeeMessage()){
            result.setMessage(((JeeException)e).getJeeMessage());
        }else {
            result.setCode(-1);
            if (null == e.getMessage()){
                result.setMessage("系统异常：" + e.toString());
            }else {
                result.setMessage(e.getMessage());
            }
            e.printStackTrace();
        }
        return result;
    }
}
