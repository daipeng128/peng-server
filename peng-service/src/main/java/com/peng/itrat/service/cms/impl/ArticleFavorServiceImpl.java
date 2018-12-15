package com.peng.itrat.service.cms.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.cms.IArticleFavorDao;
import com.peng.itrat.model.cms.ArticleFavor;
import com.peng.itrat.service.cms.IArticleFavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("articleFavorService")
public class ArticleFavorServiceImpl extends BaseServiceImpl<ArticleFavor> implements IArticleFavorService {
    @Resource
    private IArticleFavorDao articleFavorDao;

    @Override
    public ArticleFavor find(Integer articleId, Integer memberId) {
        return articleFavorDao.find(articleId,memberId);
    }

    @Override
    public void save(Integer articleId, Integer memberId) {
        articleFavorDao.save(articleId,memberId);
    }

    @Override
    public void delete(Integer articleId, Integer memberId) {
        articleFavorDao.delete(articleId,memberId);
    }
}
