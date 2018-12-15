package com.peng.itrat.service.system;

import com.lxinet.jeesns.core.service.IBaseService;
import com.peng.itrat.model.system.ScoreRule;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IScoreRuleService extends IBaseService<ScoreRule> {

    List<ScoreRule> list();

    ScoreRule findById(Integer id);

    boolean update(ScoreRule scoreRule);

    boolean enabled(int id);

}
