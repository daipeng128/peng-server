package com.peng.itrat.dao.system;

import com.peng.itrat.model.system.ActionLog;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionLogDao extends BaseMapper<ActionLog> {

    List<ActionLog> list(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<ActionLog> memberActionLog(@Param("page") Page page, @Param("memberId") Integer memberId);
}
