package com.peng.itrat.dao.common;

import com.peng.itrat.model.common.Ads;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
public interface IAdsDao extends BaseMapper<Ads> {

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    List<Ads> list(@Param("page") Page page);

    int enable(@Param("id") Integer id);
}
