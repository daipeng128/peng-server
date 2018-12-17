package com.peng.itrat.service.weibo;


import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.weibo.WeiboFavor;

/**
 * 微博点赞Service接口
 * Created by zchuanzhao on 2017/2/8.
 */
public interface IWeiboFavorService extends IBaseService<WeiboFavor> {

    WeiboFavor find(Integer weiboId, Integer memberId);

    void save(Integer weiboId, Integer memberId);

    void delete(Integer weiboId, Integer memberId);
}