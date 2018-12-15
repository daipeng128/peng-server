package com.peng.itrat.service.system;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.peng.itrat.model.system.Tag;

public interface ITagService {
    boolean save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    boolean update(Tag tag);

    boolean delete(Integer id);

    Tag findById(Integer id);

}
