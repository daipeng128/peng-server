package com.peng.itrat.service.system;

import com.lxinet.jeesns.core.service.IBaseService;
import com.peng.itrat.model.system.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/29.
 */
public interface IConfigService extends IBaseService<Config> {
    List<Config> allList();

    Map<String,String> getConfigToMap();

    String getValue(String key);

    boolean update(Map<String,String> params, HttpServletRequest request);
}
