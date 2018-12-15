package com.peng.itrat.dao.weibo;

import com.peng.itrat.model.weibo.WeiboFavor;
import com.lxinet.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 微博点赞DAO接口
 * Created by zchuanzhao on 2017/2/8.
 */
public interface IWeiboFavorDao extends BaseMapper<WeiboFavor> {

    WeiboFavor find(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer save(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer delete(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);
}