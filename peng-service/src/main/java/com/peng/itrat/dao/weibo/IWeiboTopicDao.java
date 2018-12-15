package com.peng.itrat.dao.weibo;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.peng.itrat.model.weibo.WeiboTopic;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 2018/11/14.
 */
public interface IWeiboTopicDao extends BaseMapper<WeiboTopic> {

    WeiboTopic findByName(@Param("name") String name);

}