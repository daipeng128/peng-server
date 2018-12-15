package com.peng.itrat.core.dao;

import com.peng.itrat.core.conditions.SqlWrapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.provider.SqlProvider;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseMapper<T> {
    @InsertProvider(
        type = SqlProvider.class,
        method = "saveObj"
    )
    @Options(
        useGeneratedKeys = true,
        keyProperty = "id"
    )
    int saveObj(T var1);

    @SelectProvider(
        type = SqlProvider.class,
        method = "listAll"
    )
    List<T> listAll(SqlWrapper<T> var1);

    @SelectProvider(
        type = SqlProvider.class,
        method = "listByPage"
    )
    List<T> listByPage(@Param("page") Page var1, @Param("sqlWrapper") SqlWrapper<T> var2);

    @UpdateProvider(
        type = SqlProvider.class,
        method = "updateObj"
    )
    int updateObj(T var1);

    @SelectProvider(
        type = SqlProvider.class,
        method = "getById"
    )
    T getById(@Param("id") Integer var1, @Param("c") Class<?> var2);

    @DeleteProvider(
        type = SqlProvider.class,
        method = "deleteById"
    )
    int deleteById(@Param("id") Integer var1, @Param("c") Class<?> var2);

    @DeleteProvider(
        type = SqlProvider.class,
        method = "deleteObj"
    )
    int deleteObj(T var1);
}