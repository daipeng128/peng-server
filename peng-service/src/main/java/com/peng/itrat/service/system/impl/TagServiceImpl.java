package com.peng.itrat.service.system.impl;

import com.peng.itrat.model.system.Tag;
import com.peng.itrat.service.system.ITagService;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.dao.system.ITagDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-11-01.
 */
@Service("tagService")
public class TagServiceImpl implements ITagService {
    @Resource
    private ITagDao tagDao;

    @Override
    public boolean save(Tag tag) {
        return tagDao.saveObj(tag) == 1;
    }

    @Override
    public ResultModel listByPage(Page page, int funcType) {
        List<Tag> list = tagDao.list(page,funcType);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public boolean update(Tag tag) {
        Tag findTag = this.findById(tag.getId());
        if (findTag == null) {
            throw new OpeErrorException("标签不存在");
        }
        return tagDao.updateObj(tag) == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return tagDao.deleteById(id, Tag.class) > 0;
    }

    @Override
    public Tag findById(Integer id) {
        return tagDao.getById(id, Tag.class);
    }
}
