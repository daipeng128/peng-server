package com.peng.itrat.dao.group;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.model.group.Group;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupDao extends BaseMapper<Group> {

    /**
     * 获取群组
     * @return
     */
    List<Group> list(@Param("status") Integer status, @Param("key") String key);

    /**
     * 修改状态
     *
     * @param id
     * @return
     */
    Integer changeStatus(@Param("id") Integer id);

    List<Group> listByCustom(@Param("status") int status, @Param("num") int num, @Param("sort") String sort);

    Group findById(@Param("id") Integer id);

    int save(Group entity);

    int update(Group entity);

    int delete(@Param("id") Integer id);
}