package com.peng.itrat.service.common.impl;

import com.peng.itrat.service.common.IAdsService;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.common.IAdsDao;
import com.peng.itrat.model.common.Ads;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
@Service("adsService")
public class AdsServiceImpl extends BaseServiceImpl<Ads> implements IAdsService {
    @Resource
    private IAdsDao adsDao;


    @Override
    public ResultModel listByPage(Page page) {
        List<Ads> list = adsDao.list(page);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public boolean enable(Integer id) {
        if (adsDao.enable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }
}
