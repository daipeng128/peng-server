package com.peng.itrat.web.manage;

import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.member.Checkin;
import com.peng.itrat.service.member.ICheckinService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 2018/8/20.
 */
@Controller("manageCheckinController")
@RequestMapping("/${managePath}/checkin/")
@Before(AdminLoginInterceptor.class)
public class CheckinController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/checkin/";
    @Resource
    private ICheckinService checkinService;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.list(page, 0);
        ResultModel resultModel = new ResultModel(0, page);
        resultModel.setData(list);
        model.addAttribute("model",resultModel);
        return MANAGE_FTL_PATH + "list";
    }
}
