package com.peng.itrat.service.member.impl;

import com.peng.itrat.dao.member.IFinancialDao;
import com.peng.itrat.model.member.Financial;
import com.peng.itrat.service.member.IFinancialService;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.utils.PageUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Service
public class FinancialServiceImpl extends BaseServiceImpl<Financial> implements IFinancialService {
    @Resource
    private IFinancialDao financialDao;

    @Override
    public List<Financial> list(Integer memberId) {
        List<Financial> list = financialDao.list(PageUtil.getPage(),memberId);
        return list;
    }

}
