package com.peng.itrat.service.system.impl;

import com.peng.itrat.service.system.IConfigService;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.system.IConfigDao;
import com.peng.itrat.model.system.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/27.
 */
@Service("configService")
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements IConfigService {
    @Resource
    private IConfigDao configDao;


    @Override
    public List<Config> allList() {
        return super.listAll();
    }

    @Override
    public Map<String, String> getConfigToMap() {
        List<Config> allList = this.allList();
        Map<String,String> map = new HashMap<>();
        for (Config config : allList) {
            map.put(config.getJkey(),config.getJvalue());
        }
        return map;
    }

    @Override
    public String getValue(String key) {
        return null;
    }

    @Override
    public boolean update(Map<String, String> params, HttpServletRequest request) {
        for(Map.Entry entry : params.entrySet()){
            if(((String)entry.getValue()).length() > 500){
                throw new ParamException("只能输入255个字符");
            }else {
                configDao.update((String)entry.getKey(),(String)entry.getValue());
                request.getServletContext().setAttribute(((String)entry.getKey()).toUpperCase(),entry.getValue());
            }
        }
        return true;
    }


}
