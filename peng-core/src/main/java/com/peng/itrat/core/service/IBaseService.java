package com.peng.itrat.core.service;

import com.peng.itrat.core.conditions.SqlWrapper;
import com.peng.itrat.core.model.Page;

import java.util.List;

public interface IBaseService<T> {
    T findById(Integer var1);

    List<T> listAll();

    List<T> listAll(SqlWrapper<T> var1);

    List<T> listByPage();

    List<T> listByPage(Page var1, SqlWrapper<T> var2);

    boolean save(T var1);

    boolean update(T var1);

    boolean delete(T var1);

    boolean deleteById(Integer var1);
}