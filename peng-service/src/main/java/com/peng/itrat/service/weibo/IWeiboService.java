package com.peng.itrat.service.weibo;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.weibo.Weibo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public interface IWeiboService extends IBaseService<Weibo> {

    Weibo findById(int id, int memberId);

    boolean save(HttpServletRequest request, Member loginMember, String content, String pictures);

    ResultModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key);

    boolean delete(HttpServletRequest request, Member loginMember, int id);

    boolean userDelete(HttpServletRequest request, Member loginMember, int id);

    List<Weibo> hotList(int loginMemberId);

    ResultModel favor(Member loginMember, int weiboId);

    List<Weibo> listByCustom(int loginMemberId, String sort,int num,int day);

    ResultModel<Weibo> listByTopic(Page page, int loginMemberId, String topicName);
}
