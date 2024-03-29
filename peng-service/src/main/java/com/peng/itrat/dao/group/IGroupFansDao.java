package com.peng.itrat.dao.group;

import com.peng.itrat.model.group.GroupFans;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.group.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/26.
 */
public interface IGroupFansDao extends BaseMapper<GroupFans> {

    /**
     * 获取群组粉丝
     * @return
     */
    List<GroupFans> list(@Param("page") Page page, @Param("groupId") Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int save(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int delete(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);


    /**
     * 获取用户关注的群组列表
     * @param page
     * @param memberId
     * @return
     */
    List<Group> listByMember(@Param("page") Page page, @Param("memberId") Integer memberId);

}