package com.peng.itrat.web.front;

import com.peng.itrat.interceptor.UserLoginInterceptor;
import com.peng.itrat.utils.MemberUtil;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.ItRatConfig;
import com.peng.itrat.model.member.Checkin;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.service.member.ICheckinService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 2018/8/20.
 */
@Controller
@RequestMapping("/checkin/")
public class CheckinController extends BaseController {
    @Resource
    private ICheckinService checkinService;
    @Resource
    private ItRatConfig itRatConfig;

    @RequestMapping({"","index"})
    public String index(Model model){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.todayList(page);
        ResultModel resultModel = new ResultModel(0, page);
        resultModel.setData(list);
        model.addAttribute("model",resultModel);
        model.addAttribute("todayContinueList",checkinService.todayContinueList());
        return itRatConfig.getFrontTemplate() + "/checkin/index";
    }

    @RequestMapping("save")
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel save(){
        Member member = MemberUtil.getLoginMember(request);
        return new ResultModel<>(checkinService.save(member.getId()));
    }
}
