package com.peng.itrat.core.annotation;

import com.peng.itrat.core.enums.FillTime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value();

    String defaultValue() default "";

    FillTime currTime() default FillTime.NONE;
}
