package com.peng.itrat.service.cms;


import com.peng.itrat.model.cms.ArticleFavor;
import com.lxinet.jeesns.core.service.IBaseService;

/**
 * 文章点赞Service接口
 * Created by zchuanzhao on 2018/11/21.
 */
public interface IArticleFavorService extends IBaseService<ArticleFavor> {

    ArticleFavor find(Integer articleId, Integer memberId);

    void save(Integer articleId, Integer memberId);

    void delete(Integer articleId, Integer memberId);
}