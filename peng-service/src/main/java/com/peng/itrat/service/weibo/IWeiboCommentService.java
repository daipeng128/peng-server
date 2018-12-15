package com.peng.itrat.service.weibo;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.weibo.WeiboComment;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IWeiboCommentService extends IBaseService<WeiboComment> {

    WeiboComment findById(int id);

    boolean save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId);

    boolean delete(Member loginMember, int id);

    ResultModel listByWeibo(Page page, int weiboId);

    void deleteByWeibo(Integer weiboId);
}
