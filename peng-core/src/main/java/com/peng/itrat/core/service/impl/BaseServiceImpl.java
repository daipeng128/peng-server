package com.peng.itrat.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.peng.itrat.core.conditions.SqlWrapper;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;

    public BaseServiceImpl() {
    }

    public T findById(Integer id) {
        return this.baseMapper.getById(id, this.getTClass());
    }

    public List<T> listAll() {
        SqlWrapper sqlWrapper = new SqlWrapper(this.getTClass());
        return this.baseMapper.listAll(sqlWrapper);
    }

    public List<T> listAll(SqlWrapper<T> sqlWrapper) {
        return this.baseMapper.listAll(sqlWrapper);
    }

    public List<T> listByPage() {
        SqlWrapper sqlWrapper = new SqlWrapper(this.getTClass());
        return this.baseMapper.listByPage(PageUtil.getPage(), sqlWrapper);
    }

    public List<T> listByPage(Page page, SqlWrapper<T> sqlWrapper) {
        return this.baseMapper.listByPage(page, sqlWrapper);
    }

    public boolean save(T t) {
        return this.baseMapper.saveObj(t) > 0;
    }

    public boolean update(T t) {
        return this.baseMapper.updateObj(t) > 0;
    }

    public boolean delete(T t) {
        return this.baseMapper.deleteObj(t) > 0;
    }

    public boolean deleteById(Integer id) {
        return this.baseMapper.deleteById(id, this.getTClass()) > 0;
    }

    private Class<T> getTClass() {
        Class tClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
