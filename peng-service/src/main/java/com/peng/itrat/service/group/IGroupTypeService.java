package com.peng.itrat.service.group;

import com.peng.itrat.model.group.GroupType;
import com.peng.itrat.core.service.IBaseService;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午11:13
 */
public interface IGroupTypeService extends IBaseService<GroupType> {

    List<GroupType> list();

    boolean delete(int id);

}
