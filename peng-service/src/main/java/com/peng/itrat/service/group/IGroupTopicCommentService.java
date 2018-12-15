package com.peng.itrat.service.group;

import com.peng.itrat.model.member.Member;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;
import com.peng.itrat.model.group.GroupTopicComment;


/**
 * Created by zchuanzhao on 2016/12/27.
 */
public interface IGroupTopicCommentService extends IBaseService<GroupTopicComment> {

    GroupTopicComment findById(int id);

    boolean save(Member loginMember, String content, Integer groupTopicId, Integer commentId);

    boolean delete(Member loginMember, int id);

    ResultModel listByGroupTopic(Page page, int groupTopicId);

    void deleteByTopic(int groupTopicId);
}
