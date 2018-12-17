package com.peng.itrat.service.common;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.common.Link;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkService extends IBaseService<Link> {

    ResultModel listByPage(Page page);

    ResultModel allList();

    ResultModel recommentList();

    boolean enable(Integer id);
}
