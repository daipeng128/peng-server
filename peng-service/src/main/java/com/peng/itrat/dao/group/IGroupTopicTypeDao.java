package com.peng.itrat.dao.group;

import com.peng.itrat.model.group.GroupTopicType;
import com.peng.itrat.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午10:59
 */
public interface IGroupTopicTypeDao extends BaseMapper<GroupTopicType> {

    List<GroupTopicType> list(@Param("groupId") Integer groupId);

}