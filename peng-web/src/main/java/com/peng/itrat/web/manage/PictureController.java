package com.peng.itrat.web.manage;

import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.consts.AppTag;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.service.picture.IPictureService;
import com.peng.itrat.service.system.ITagService;
import com.peng.itrat.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/01
 */
@Controller
@RequestMapping("/${managePath}/picture")
@Before(AdminLoginInterceptor.class)
public class PictureController extends BaseController{
    private static final String MANAGE_FTL_PATH = "/manage/picture/";
    @Resource
    private IPictureService pictureService;
    @Resource
    private ITagService tagService;

    @RequestMapping("/tagList")
    public String tagList(Model model){
        Page page = new Page(request);
        ResultModel resultModel = tagService.listByPage(page, AppTag.PICTURE);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "tagList";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Page page = new Page(request);
        ResultModel resultModel = pictureService.listByPage(page,0);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "/delete/{pictureId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("pictureId") Integer pictureId){
        return new ResultModel(pictureService.delete(request,pictureId));
    }

}
