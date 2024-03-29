package com.peng.itrat.dao.group;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.model.group.GroupTopicFavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 文章点赞DAO接口
 * Created by zchuanzhao on 2017/2/9.
 */
public interface IGroupTopicFavorDao extends BaseMapper<GroupTopicFavor> {

    @Select("select * from tbl_group_topic_favor where group_topic_id = #{groupTopicId} and member_id = #{memberId}")
    GroupTopicFavor find(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);

    @Insert("insert into tbl_group_topic_favor (group_topic_id,member_id,create_time) values (#{groupTopicId}, #{memberId}, now())")
    Integer save(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);

    @Delete("delete from tbl_group_topic_favor where group_topic_id = #{groupTopicId} and member_id = #{memberId}")
    Integer delete(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);
}