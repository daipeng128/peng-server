package com.peng.itrat.service.system;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.system.Tag;

public interface ITagService {
    boolean save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    boolean update(Tag tag);

    boolean delete(Integer id);

    Tag findById(Integer id);

}
