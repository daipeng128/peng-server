package com.peng.itrat.service.common.impl;

import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.peng.itrat.dao.common.ILinkDao;
import com.peng.itrat.model.common.Link;
import com.peng.itrat.service.common.ILinkService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
@Service("linkService")
public class LinkServiceImpl extends BaseServiceImpl<Link> implements ILinkService {
    @Resource
    private ILinkDao linkDao;

    @Override
    public ResultModel listByPage(Page page) {
        List<Link> list = linkDao.list(page);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel allList() {
        List<Link> list = linkDao.listAll(new SqlWrapper<>(Link.class));
        ResultModel model = new ResultModel(0);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel recommentList() {
        List<Link> list = linkDao.recommentList();
        ResultModel model = new ResultModel(0);
        model.setData(list);
        return model;
    }

    @Override
    public boolean enable(Integer id) {
       return linkDao.enable(id) == 1;
    }
}
