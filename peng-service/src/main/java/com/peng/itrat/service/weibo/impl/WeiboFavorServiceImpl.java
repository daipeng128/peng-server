package com.peng.itrat.service.weibo.impl;

import com.peng.itrat.dao.weibo.IWeiboFavorDao;
import com.peng.itrat.model.weibo.WeiboFavor;
import com.peng.itrat.service.weibo.IWeiboFavorService;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/8.
 */
@Service("weiboFavorService")
public class WeiboFavorServiceImpl extends BaseServiceImpl<WeiboFavor> implements IWeiboFavorService {
    @Resource
    private IWeiboFavorDao weiboFavorDao;


    @Override
    public WeiboFavor find(Integer weiboId, Integer memberId) {
        return weiboFavorDao.find(weiboId,memberId);
    }

    @Override
    public void save(Integer weiboId, Integer memberId) {
        weiboFavorDao.save(weiboId,memberId);
    }

    @Override
    public void delete(Integer weiboId, Integer memberId) {
        weiboFavorDao.delete(weiboId,memberId);
    }
}
