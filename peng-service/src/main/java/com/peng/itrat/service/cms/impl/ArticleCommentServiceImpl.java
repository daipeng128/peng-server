package com.peng.itrat.service.cms.impl;

import com.peng.itrat.dao.cms.IArticleCommentDao;
import com.peng.itrat.model.cms.Article;
import com.peng.itrat.model.cms.ArticleComment;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.service.cms.IArticleCommentService;
import com.peng.itrat.service.cms.IArticleService;
import com.peng.itrat.service.member.IMemberService;
import com.peng.itrat.service.member.IMessageService;
import com.peng.itrat.service.member.IScoreDetailService;
import com.peng.itrat.service.system.IActionLogService;
import com.peng.itrat.utils.ActionUtil;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.core.utils.ValidUtill;
import com.peng.itrat.core.consts.AppTag;
import com.peng.itrat.core.enums.MessageType;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.StringUtils;
import com.peng.itrat.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/10/14.
 */
@Service("articleCommentService")
public class ArticleCommentServiceImpl extends BaseServiceImpl<ArticleComment> implements IArticleCommentService {
    @Resource
    private IArticleCommentDao articleCommentDao;
    @Resource
    private IArticleService articleService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public ArticleComment findById(Integer id) {
        return this.atFormat(super.findById(id));
    }

    @Override
    public boolean save(Member loginMember, String content, Integer articleId) {
        Article article = articleService.findById(articleId);
        ValidUtill.checkIsNull(article, "文章不存在");
        ValidUtill.checkIsBlank(content, "内容不能为空");
        ArticleComment articleComment = new ArticleComment();
        articleComment.setMemberId(loginMember.getId());
        articleComment.setArticleId(articleId);
        articleComment.setContent(content);
        boolean result = super.save(articleComment);
        if(!result){
            throw new OpeErrorException();
        }
        //@会员处理并发送系统消息
        messageService.atDeal(loginMember.getId(),content, AppTag.CMS, MessageType.CMS_ARTICLE_COMMENT_REFER,articleComment.getId());
        messageService.diggDeal(loginMember.getId(),article.getMemberId(),content,AppTag.CMS,MessageType.CMS_ARTICLR_REPLY,article.getId());
        //文章评论奖励
        scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_REVIEWS,articleComment.getId());
        return true;
    }

    @Override
    public List listByPage(Page page, int articleId, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<ArticleComment> list = articleCommentDao.list(page, articleId, key);
        this.atFormat(list);
        return list;
    }

    @Override
    public void deleteByArticle(Integer articleId) {
        articleCommentDao.deleteByArticle(articleId);
    }

    @Override
    @Transactional
    public boolean delete(Member loginMember, int id) {
        boolean result = super.deleteById(id);
        if(!result){
            throw new OpeErrorException();
        }
        //扣除积分
        scoreDetailService.scoreCancelBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_REVIEWS,id);
        actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_ARTICLE_COMMENT,"ID："+id);
        return true;
    }

    public ArticleComment atFormat(ArticleComment articleComment){
        articleComment.setContent(memberService.atFormat(articleComment.getContent()));
        return articleComment;
    }

    public List<ArticleComment> atFormat(List<ArticleComment> articleCommentList){
        for (ArticleComment articleComment : articleCommentList){
            atFormat(articleComment);
        }
        return articleCommentList;
    }
}
