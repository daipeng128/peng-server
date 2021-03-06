package com.peng.itrat.service.system.impl;

import com.peng.itrat.model.system.Action;
import com.peng.itrat.service.system.IActionService;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.system.IActionDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Service("actionService")
public class ActionServiceImpl extends BaseServiceImpl<Action> implements IActionService {
    @Resource
    private IActionDao actionDao;

    @Override
    public List<Action> list() {
        return super.listAll();
    }

    @Override
    public Action findById(Integer id) {
        return super.findById(id);
    }


    @Override
    public boolean isenable(Integer id) {
        if(actionDao.isenable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    /**
     * 状态是否能使用
     * @param id
     * @return true可以使用，false不能使用
     */
    @Override
    public boolean canuse(Integer id) {
        Action action = this.findById(id);
        if(action == null){
            return false;
        }
        if(action.getStatus() == 1){
            return false;
        }
        return true;
    }
}
