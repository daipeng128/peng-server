package com.peng.itrat.web.manage;

import com.peng.itrat.interceptor.AdminLoginInterceptor;
import com.peng.itrat.core.annotation.Before;
import com.peng.itrat.core.annotation.UsePage;
import com.peng.itrat.core.conditions.SqlWrapper;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.invoke.JeesnsInvoke;
import com.peng.itrat.core.utils.PageUtil;
import com.peng.itrat.model.member.Cardkey;
import com.peng.itrat.model.member.MemberLevel;
import com.peng.itrat.web.common.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/27.
 */
@Controller("manageCardkeyController")
@RequestMapping("${managePath}/member/cardkey/")
@Before(AdminLoginInterceptor.class)
public class CardkeyController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/member/cardkey/";
    private static final String EXT_CARDKEY_CLASS = "extCardkeyService";

    @GetMapping("list")
    @UsePage
    public String list(Model model) {
        SqlWrapper<Cardkey> sqlWrapper = new SqlWrapper<>(Cardkey.class);
        sqlWrapper.order("id", SqlWrapper.DESC);
        List<MemberLevel> list = (List<MemberLevel>) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "listByPage", PageUtil.getPage(), sqlWrapper);
        ResultModel resultModel = new ResultModel(0,PageUtil.getPage());
        resultModel.setData(list);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("add")
    public String add() {
        return MANAGE_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultModel save(@Param("num") Integer num, @Param("money") Double money, @Param("expireTime") String expireTime) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "save",num, money, expireTime);
        return new ResultModel(boo);
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "deleteById",id);
        return new ResultModel(boo);
    }


}
