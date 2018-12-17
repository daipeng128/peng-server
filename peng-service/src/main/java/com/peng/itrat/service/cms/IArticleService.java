package com.peng.itrat.service.cms;

import com.peng.itrat.model.cms.Article;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;

import java.util.List;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleService extends IBaseService<Article> {


    Article findById(int id,Member loginMember);

    boolean save(Member member, Article article);

    boolean update(Member member, Article article);

    boolean delete(Member member, int id);

    ResultModel listByPage(Page page, String key, int cateid, int status, int memberId);

    void updateViewCount(int id);

    boolean audit(int id);

    ResultModel favor(Member loginMember, int articleId);

    List<Article> listByCustom(int cid,String sort,int num,int day,int thumbnail);
}
