package com.peng.itrat.service.group;

import com.peng.itrat.model.group.GroupFans;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 16/12/26.
 */
public interface IGroupFansService extends IBaseService<GroupFans> {

    boolean save(Member loginMember, Integer groupId);

    boolean delete(Member loginMember, Integer groupId);

    ResultModel listByPage(Page page, Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId, @Param("memberId") Integer memberId);

    /**
     * 获取用户关注的群组列表
     * @param page
     * @param memberId
     * @return
     */
    ResultModel listByMember(Page page, Integer memberId);
}
