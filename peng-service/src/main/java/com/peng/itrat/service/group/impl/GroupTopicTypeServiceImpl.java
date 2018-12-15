package com.peng.itrat.service.group.impl;

import com.peng.itrat.dao.group.IGroupTopicTypeDao;
import com.peng.itrat.model.group.GroupTopicType;
import com.peng.itrat.service.group.IGroupTopicTypeService;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/9 下午1:17
 */
@Service
public class GroupTopicTypeServiceImpl extends BaseServiceImpl<GroupTopicType> implements IGroupTopicTypeService {
    @Resource
    private IGroupTopicTypeDao groupTopicTypeDao;

    @Override
    public GroupTopicType findById(int id) {
        return super.findById(id);
    }

    @Override
    public List<GroupTopicType> list(int groupId) {
        return groupTopicTypeDao.list(groupId);
    }


}
