package com.peng.itrat.web.manage;

import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.DateFormatUtil;
import com.peng.itrat.model.common.Ads;
import com.peng.itrat.service.common.IAdsService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
@Controller
@RequestMapping("/${managePath}/ads")
@Before(AdminLoginInterceptor.class)
public class AdsController extends BaseController{
    private static final String MANAGE_FTL_PATH = "/manage/ads/";
    @Resource
    private IAdsService adsService;

    @RequestMapping("/list")
    public String list(Model model){
        Page page = new Page(request);
        ResultModel resultModel = adsService.listByPage(page);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("/add")
    public String add(){
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultModel save(Ads ads){
        String startTimeStr = getParam("startDateTime");
        String endTimeStr = getParam("endDateTime");
        ads.setStartTime(DateFormatUtil.formatDateTime(startTimeStr));
        ads.setEndTime(DateFormatUtil.formatDateTime(endTimeStr));
        ads.setContent(ads.getContent().replace("&lt;","<").replace("&gt;",">").replace("&#47;","/"));
        return new ResultModel(adsService.save(ads));
    }


    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Ads ads = adsService.findById(id);
        model.addAttribute("ads",ads);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultModel update(Ads ads){
        String startTimeStr = getParam("startDateTime");
        String endTimeStr = getParam("endDateTime");
        ads.setStartTime(DateFormatUtil.formatDateTime(startTimeStr));
        ads.setEndTime(DateFormatUtil.formatDateTime(endTimeStr));
        ads.setContent(ads.getContent().replace("&lt;","<").replace("&gt;",">").replace("&#47;","/"));
        return new ResultModel(adsService.update(ads));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(adsService.deleteById(id));
    }

    @RequestMapping("/enable/{id}")
    @ResponseBody
    public ResultModel enable(@PathVariable("id") Integer id){
        return new ResultModel(adsService.enable(id));
    }


}
