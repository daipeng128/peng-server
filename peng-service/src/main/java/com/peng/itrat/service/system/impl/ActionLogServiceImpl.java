package com.peng.itrat.service.system.impl;

import com.peng.itrat.dao.system.IActionLogDao;
import com.peng.itrat.model.system.Action;
import com.peng.itrat.model.system.ActionLog;
import com.peng.itrat.service.system.IActionLogService;
import com.peng.itrat.service.system.IActionService;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Service("actionLogService")
public class ActionLogServiceImpl extends BaseServiceImpl<ActionLog> implements IActionLogService {
    @Resource
    private IActionService actionService;
    @Resource
    private IActionLogDao actionLogDao;

    @Override
    public ResultModel<ActionLog> listByPage(Page page, Integer memberId) {
        List<ActionLog> list = actionLogDao.list(page, memberId);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel<ActionLog> memberActionLog(Page page, Integer memberId) {
        List<ActionLog> list = actionLogDao.memberActionLog(page, memberId);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ActionLog findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public void save(String actionIp, Integer memberId, Integer actionId) {
        this.save(actionIp,memberId,actionId,"",0,0);
    }

    @Override
    public void save(String actionIp, Integer memberId, Integer actionId, String remark) {
        this.save(actionIp,memberId,actionId,remark,0,0);
    }

    @Override
    public void save(String actionIp, Integer memberId, Integer actionId, String remark, Integer type, Integer foreignId) {
        Action action = actionService.findById(actionId);
        if(action != null){
            if(action.getStatus() == 0){
                ActionLog actionLog = new ActionLog(memberId,actionId,remark,actionIp,type,foreignId);
                super.save(actionLog);
            }
        }
    }

}
