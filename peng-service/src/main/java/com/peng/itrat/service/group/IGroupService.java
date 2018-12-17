package com.peng.itrat.service.group;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.group.Group;

import java.util.List;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupService extends IBaseService<Group> {

    Group findById(int id);

    boolean save(Member loginMember, Group group);

    boolean update(Member loginMember, Group group);

    boolean delete(Member loginMember, int id);

    List<Group> list(int status, String key);

    boolean follow(Member loginMember, Integer groupId, int type);

    boolean changeStatus(int id);

    List<Group> listByCustom(int status, int num, String sort);
}
