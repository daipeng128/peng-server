package com.peng.itrat.web.front;

import com.peng.itrat.core.utils.StringUtils;
import com.peng.itrat.utils.MemberUtil;
import com.peng.itrat.core.enums.Messages;
import com.peng.itrat.core.exception.NotFountException;
import com.peng.itrat.core.exception.ParamException;
import com.peng.itrat.interceptor.UserLoginInterceptor;
import com.peng.itrat.model.cms.ArticleComment;
import com.peng.itrat.service.common.IArchiveService;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.ItRatConfig;
import com.peng.itrat.web.common.BaseController;
import com.peng.itrat.model.cms.ArticleCate;
import com.peng.itrat.model.cms.Article;
import com.peng.itrat.service.cms.IArticleCateService;
import com.peng.itrat.service.cms.IArticleCommentService;
import com.peng.itrat.service.cms.IArticleService;
import com.peng.itrat.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 前台文章Controller
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("frontArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Resource
    private ItRatConfig itRatConfig;
    @Resource
    private IArticleCateService articleCateService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IArticleCommentService articleCommentService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "cid",defaultValue = "0",required = false) Integer cid,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        if (StringUtils.isNotEmpty(key)){
            try {
                key = new String(key.getBytes("iso-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Page page = new Page(request);
        ResultModel resultModel = articleService.listByPage(page,key,cid,1,memberId);
        model.addAttribute("model", resultModel);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        ArticleCate articleCate = articleCateService.findById(cid);
        model.addAttribute("articleCate",articleCate);
        return itRatConfig.getFrontTemplate() + "/cms/list";
    }

    @RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        //文章不存在或者访问未审核的文章，跳到错误页面，提示文章不存在
        if(article == null || article.getStatus() == 0){
            throw new NotFountException(Messages.ARTICLE_NOT_EXISTS);
        }
        //更新文章访问次数
        articleService.updateViewCount(article.getId());
        model.addAttribute("article",article);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        model.addAttribute("loginUser",loginMember);
        return itRatConfig.getFrontTemplate() + "/cms/detail";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        return itRatConfig.getFrontTemplate() + "/cms/add";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResultModel(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = new ResultModel(articleService.save(loginMember,article));
        if(resultModel.getCode() == 0){
            resultModel.setCode(2);
            //文章需要审核就跳转到列表页面
            if(article.getStatus() == 0){
                resultModel.setMessage("文章发布成功，请等待审核");
                resultModel.setUrl(request.getContextPath()+"/article/list");
            }else {
                resultModel.setUrl(request.getContextPath()+"/article/detail/"+article.getId());
            }
        }
        return resultModel;
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String edit(@PathVariable("id") int id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        if(article.getMemberId().intValue() != loginMember.getId().intValue()){
            throw new NotFountException(Messages.ARTICLE_NOT_EXISTS);
        }
        model.addAttribute("article",article);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        model.addAttribute("loginUser", loginMember);
        return itRatConfig.getFrontTemplate() + "/cms/edit";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            new ResultModel(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new ResultModel(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = new ResultModel(articleService.update(loginMember,article));
        if(resultModel.getCode() == 0){
            resultModel.setCode(2);
            resultModel.setUrl(request.getContextPath() + "/article/detail/"+article.getId());
        }
        return resultModel;
    }

    /**
     * 评论文章
     * @param articleId
     * @param content
     * @return
     */
    @RequestMapping(value="/comment/{articleId}",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel comment(@PathVariable("articleId") Integer articleId, String content){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(articleCommentService.save(loginMember,content,articleId));
    }


    @RequestMapping(value="/commentList/{articleId}.json",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel commentList(@PathVariable("articleId") Integer articleId){
        Page page = new Page(request);
        if(articleId == null){
            articleId = 0;
        }
        List<ArticleComment> list = articleCommentService.listByPage(page,articleId, null);
        ResultModel resultModel = new ResultModel(0,page);
        resultModel.setData(list);
        return resultModel;
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 0){
            return new ResultModel(-1,"权限不足");
        }
        ResultModel resultModel = new ResultModel(articleService.delete(loginMember,id));
        if(resultModel.getCode() > 0){
            resultModel.setCode(2);
            resultModel.setUrl(request.getContextPath() + "/article/list");
        }
        return resultModel;
    }


    /**
     * 文章、喜欢
     * @param id
     * @return
     */
    @RequestMapping(value="/favor/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel favor(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(id == null) {
            throw new ParamException();
        }
        ResultModel resultModel = articleService.favor(loginMember,id);
        return resultModel;
    }
}
