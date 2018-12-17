package com.peng.itrat.service.cms;

import com.peng.itrat.model.cms.ArticleComment;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;

import java.util.List;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleCommentService extends IBaseService<ArticleComment> {

    boolean save(Member loginMember, String content, Integer articleId);

    boolean delete(Member loginMember, int id);

    List<ArticleComment> listByPage(Page page, int articleId, String key);

    void deleteByArticle(Integer articleId);
}
