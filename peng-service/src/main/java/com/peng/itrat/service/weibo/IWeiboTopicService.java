package com.peng.itrat.service.weibo;

import com.lxinet.jeesns.core.service.IBaseService;
import com.peng.itrat.model.weibo.WeiboTopic;


/**
 * Created by zchuanzhao on 2018/11/14.
 */
public interface IWeiboTopicService extends IBaseService<WeiboTopic> {

    WeiboTopic findByName(String name);

}
