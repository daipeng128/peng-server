package com.peng.itrat.web.front;

import com.peng.itrat.interceptor.UserLoginInterceptor;
import com.peng.itrat.utils.MemberUtil;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.web.common.BaseController;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.member.ScoreDetail;
import com.peng.itrat.service.member.IScoreDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/4/7.
 */
@Controller("scoreDetailFrontController")
@RequestMapping("/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private IScoreDetailService scoreDetailService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResultModel<ScoreDetail> resultModel = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model", resultModel);
        return INDEX_FTL_PATH + "list";
    }
}
