package com.peng.itrat.dao.group;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.group.GroupTopicComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/27.
 */
public interface IGroupTopicCommentDao extends BaseMapper<GroupTopicComment> {

    List<GroupTopicComment> listByGroupTopic(@Param("page") Page page, @Param("groupTopicId") Integer groupTopicId);

    int deleteByTopic(@Param("groupTopicId") Integer groupTopicId);

    GroupTopicComment findById(@Param("id") Integer id);

}