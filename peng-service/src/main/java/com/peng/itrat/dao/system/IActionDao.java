package com.peng.itrat.dao.system;

import com.peng.itrat.model.system.Action;
import com.lxinet.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionDao extends BaseMapper<Action> {
    int isenable(@Param("id") Integer id);
}
