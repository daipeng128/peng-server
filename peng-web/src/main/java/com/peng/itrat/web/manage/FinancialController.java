package com.peng.itrat.web.manage;

import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.annotation.UsePage;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.utils.PageUtil;
import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.model.member.Financial;
import com.peng.itrat.service.member.IFinancialService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Controller
@RequestMapping("/${managePath}/member/financial/")
@Before(AdminLoginInterceptor.class)
public class FinancialController extends BaseController{
    private static final String MANAGE_FTL_PATH = "/manage/member/financial/";
    @Resource
    private IFinancialService financialService;

    @UsePage
    @RequestMapping("list")
    public String list(Model model, @RequestParam(value = "memberId",required = false, defaultValue = "0") Integer memberId){
        List<Financial> list = financialService.list(memberId);
        ResultModel resultModel = new ResultModel(0, PageUtil.getPage());
        resultModel.setData(list);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

}
