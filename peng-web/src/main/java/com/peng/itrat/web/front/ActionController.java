package com.peng.itrat.web.front;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.JeesnsConfig;
import com.peng.itrat.web.common.BaseController;
import com.peng.itrat.model.system.ActionLog;
import com.peng.itrat.service.system.IActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 动态
 * Created by zchuanzhao on 2017/3/8.
 */
@Controller("frontActionController")
@RequestMapping("/action/")
public class ActionController extends BaseController {
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page(request);
        ResultModel<ActionLog> actionList = actionLogService.memberActionLog(page,0);
        model.addAttribute("model", actionList);
        return jeesnsConfig.getFrontTemplate() + "/action/list";
    }


}
