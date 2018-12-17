package com.peng.itrat.service.system.impl;

import com.peng.itrat.dao.system.IScoreRuleDao;
import com.peng.itrat.service.system.IScoreRuleService;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.model.system.ScoreRule;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Service
public class ScoreRuleServiceImpl extends BaseServiceImpl<ScoreRule> implements IScoreRuleService {
    @Resource
    private IScoreRuleDao scoreRuleDao;


    @Override
    public List<ScoreRule> list() {
        return super.listAll();
    }

    @Override
    public ScoreRule findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public boolean update(ScoreRule scoreRule) {
        return super.update(scoreRule);
    }

    @Override
    public boolean enabled(int id) {
        return scoreRuleDao.enabled(id) == 1;
    }

}
