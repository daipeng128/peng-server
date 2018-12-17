package com.peng.itrat.service.weibo.impl;

import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.weibo.IWeiboTopicDao;
import com.peng.itrat.model.weibo.WeiboTopic;
import com.peng.itrat.service.weibo.IWeiboTopicService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Service("weiboTopicService")
public class WeiboTopicServiceImpl extends BaseServiceImpl<WeiboTopic> implements IWeiboTopicService {
    @Resource
    private IWeiboTopicDao weiboTopicDao;

    @Override
    public WeiboTopic findByName(String name) {
        return weiboTopicDao.findByName(name);
    }

}
