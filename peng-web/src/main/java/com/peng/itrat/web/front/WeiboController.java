package com.peng.itrat.web.front;

import com.peng.itrat.interceptor.UserLoginInterceptor;
import com.peng.itrat.utils.MemberUtil;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.exception.NotFountException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.Const;
import com.peng.itrat.core.utils.ItRatConfig;
import com.peng.itrat.utils.ValidLoginUtill;
import com.peng.itrat.web.common.BaseController;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.weibo.Weibo;
import com.peng.itrat.service.weibo.IWeiboCommentService;
import com.peng.itrat.service.weibo.IWeiboService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/20.
 */
@Controller("frontWeiboController")
@RequestMapping("/${weiboPath}")
public class WeiboController extends BaseController {
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IWeiboCommentService weiboCommentService;
    @Resource
    private ItRatConfig itRatConfig;

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel publish(String content, String pictures){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(weiboService.save(request, loginMember,content, pictures));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResultModel resultModel = weiboService.listByPage(page,0,loginMemberId,key);
        model.addAttribute("model", resultModel);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        return itRatConfig.getFrontTemplate() + "/weibo/list";
    }

    @RequestMapping(value = "/detail/{weiboId}",method = RequestMethod.GET)
    public String detail(@PathVariable("weiboId") Integer weiboId, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Weibo weibo = weiboService.findById(weiboId,loginMemberId);
        if (weibo == null){
            throw new NotFountException("微博不存在");
        }
        model.addAttribute("weibo",weibo);
        model.addAttribute("loginUser", loginMember);
        return itRatConfig.getFrontTemplate() + "/weibo/detail";
    }

    @RequestMapping(value="/delete/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel delete(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean result = weiboService.userDelete(request, loginMember,weiboId);
        ResultModel resultModel = new ResultModel(result);
        if(resultModel.getCode() >= 0){
            resultModel.setCode(2);
            resultModel.setUrl(Const.WEIBO_PATH + "/list");
        }
        return resultModel;
    }


    @RequestMapping(value="/comment/{weiboId}",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel comment(@PathVariable("weiboId") Integer weiboId, String content, Integer weiboCommentId){
        Member loginMember = MemberUtil.getLoginMember(request);
        ValidLoginUtill.checkLogin(loginMember);
        return new ResultModel(weiboCommentService.save(loginMember,content,weiboId,weiboCommentId));
    }

    @RequestMapping(value="/commentList/{weiboId}.json",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel commentList(@PathVariable("weiboId") Integer weiboId){
        Page page = new Page(request);
        return weiboCommentService.listByWeibo(page,weiboId);
    }

    @RequestMapping(value="/favor/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel favor(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = weiboService.favor(loginMember,weiboId);
        return resultModel;
    }


    @RequestMapping(value = "/topic/{topicName}",method = RequestMethod.GET)
    public String listByTopic(@PathVariable(value = "topicName") String topicName, Model model){
        Page page = new Page(request);
        try {
            topicName = URLDecoder.decode(topicName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResultModel resultModel = null;
        resultModel = weiboService.listByTopic(page,loginMemberId,topicName);
        model.addAttribute("model", resultModel);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        model.addAttribute("topicName", topicName);
        return itRatConfig.getFrontTemplate() + "/weibo/topic";
    }

}
