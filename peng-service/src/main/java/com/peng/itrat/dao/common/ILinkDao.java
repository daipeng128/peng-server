package com.peng.itrat.dao.common;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.common.Link;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkDao extends BaseMapper<Link> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<Link> list(@Param("page") Page page);

    List<Link> recommentList();

    int enable(@Param("id") Integer id);
}
