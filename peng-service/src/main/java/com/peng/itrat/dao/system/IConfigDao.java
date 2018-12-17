package com.peng.itrat.dao.system;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.model.system.Config;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */

public interface IConfigDao extends BaseMapper<Config> {

    boolean update(@Param("key") String key,@Param("value") String value);

    String getValue(@Param("key") String key);
}