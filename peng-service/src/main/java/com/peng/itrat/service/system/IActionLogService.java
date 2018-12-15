package com.peng.itrat.service.system;

import com.peng.itrat.model.system.ActionLog;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionLogService extends IBaseService<ActionLog> {

    ResultModel<ActionLog> listByPage(Page page, Integer memberId);

    ResultModel<ActionLog> memberActionLog(Page page, Integer memberId);

    ActionLog findById(Integer id);

    void save(String actionIp,Integer memberId, Integer actionId);

    void save(String actionIp,Integer memberId, Integer actionId,String remark);

    void save(String actionIp,Integer memberId, Integer actionId,String remark, Integer type, Integer foreignId);

}
