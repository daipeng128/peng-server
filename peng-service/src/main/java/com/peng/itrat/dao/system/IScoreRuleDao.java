package com.peng.itrat.dao.system;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.peng.itrat.model.system.ScoreRule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
public interface IScoreRuleDao extends BaseMapper<ScoreRule> {

    int enabled(@Param("id") int id);
}
