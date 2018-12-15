package com.peng.itrat.web.manage;

import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.service.weibo.IWeiboService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("mamageWeiboController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class WeiboController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/weibo/";
    @Resource
    private IWeiboService weiboService;

    @RequestMapping("${managePath}/weibo/index")
    @Before(AdminLoginInterceptor.class)
    public String index(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model) {
        Page page = new Page(request);
        ResultModel resultModel = weiboService.listByPage(page,0,0,key);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/weibo/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("id") int id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(weiboService.delete(request, loginMember,id));
    }
}
