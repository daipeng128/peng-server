package com.peng.itrat.dao.system;

import com.peng.itrat.model.system.Tag;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITagDao extends BaseMapper<Tag> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Tag> list(@Param("page") Page page, @Param("funcType") int funcType);

}
