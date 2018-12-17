package com.peng.itrat.service.system;

import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.system.Action;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionService extends IBaseService<Action> {

    List<Action> list();

    Action findById(Integer id);

    boolean isenable(Integer id);

    boolean canuse(Integer id);
}
