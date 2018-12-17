package com.peng.itrat.service.group.impl;

import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.group.IGroupTypeDao;
import com.peng.itrat.model.group.GroupType;
import com.peng.itrat.service.group.IGroupTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Service
public class GroupTypeServiceImpl extends BaseServiceImpl<GroupType> implements IGroupTypeService {
    @Resource
    private IGroupTypeDao groupTypeDao;


    @Override
    public List<GroupType> list() {
        return super.listAll();
    }

    @Override
    public boolean delete(int id) {
        if (id == 1){
            throw new OpeErrorException("默认分类无法删除");
        }
        boolean result = super.deleteById(id);
        return result;
    }

}
