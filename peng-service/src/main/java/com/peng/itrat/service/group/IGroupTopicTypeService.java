package com.peng.itrat.service.group;

import com.peng.itrat.model.group.GroupTopicType;
import com.lxinet.jeesns.core.service.IBaseService;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午11:13
 */
public interface IGroupTopicTypeService extends IBaseService<GroupTopicType> {

    GroupTopicType findById(int id);

    List<GroupTopicType> list(int groupId);


}
