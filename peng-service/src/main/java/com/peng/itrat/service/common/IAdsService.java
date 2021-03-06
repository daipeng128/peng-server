package com.peng.itrat.service.common;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.common.Ads;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
public interface IAdsService extends IBaseService<Ads> {

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    ResultModel listByPage(Page page);

    boolean enable(Integer id);
}
