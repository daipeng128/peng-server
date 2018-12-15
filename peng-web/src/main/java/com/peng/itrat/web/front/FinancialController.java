package com.peng.itrat.web.front;

import com.peng.itrat.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.peng.itrat.model.member.Financial;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.service.member.IFinancialService;
import com.peng.itrat.utils.MemberUtil;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Controller("frontFinancialController")
@RequestMapping("/member/financial/")
@Before(UserLoginInterceptor.class)
public class FinancialController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/financial/";
    @Resource
    private IFinancialService financialService;

    @UsePage
    @GetMapping("list")
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        List<Financial> list = financialService.list(loginMember.getId());
        ResultModel resultModel = new ResultModel(0, PageUtil.getPage());
        resultModel.setData(list);
        model.addAttribute("model", resultModel);
        return INDEX_FTL_PATH + "list";
    }
}
