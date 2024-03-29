package com.peng.itrat.utils;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.utils.Const;
import com.peng.itrat.core.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zchuanzhao on 16/9/29.
 */
public class MemberUtil {
    public static Member getLoginMember(HttpServletRequest request){
        Member loginMember = (Member) request.getSession().getAttribute(Const.SESSION_MEMBER);
        return loginMember;
    }

    public static void setLoginMember(HttpServletRequest request,Member member){
        request.getSession().setAttribute(Const.SESSION_MEMBER,member);
    }

    public static String judgeLoginJump(HttpServletRequest request,String redirectUrl){
        Member member = getLoginMember(request);
        if(member == null){
            String redirect = "redirect:/member/login";
            if(StringUtils.isNotEmpty(redirectUrl)){
                redirect += "?redirectUrl="+request.getContextPath() + redirectUrl;
            }
            return redirect;
        }
        return null;
    }
}
