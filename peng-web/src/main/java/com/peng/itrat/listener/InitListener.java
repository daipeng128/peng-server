package com.peng.itrat.listener;

import com.peng.itrat.core.utils.Const;
import com.peng.itrat.core.utils.ItRatConfig;
import com.peng.itrat.core.utils.SpringContextUtil;
import com.peng.itrat.model.system.Config;
import com.peng.itrat.service.system.IConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/25.
 */
public class InitListener implements ServletContextListener {

    public InitListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Const.PROJECT_PATH = sce.getServletContext().getContextPath();
            sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            ItRatConfig itRatConfig = SpringContextUtil.getBean("itRatConfig");
            sce.getServletContext().setAttribute("itRatConfig",itRatConfig);
            String frontTemplate = itRatConfig.getFrontTemplate();
            sce.getServletContext().setAttribute("frontTemplate",frontTemplate);
            String managePath = Const.PROJECT_PATH + "/" + itRatConfig.getManagePath();
            Const.GROUP_PATH = Const.PROJECT_PATH + "/" + itRatConfig.getGroupPath();
            Const.WEIBO_PATH = Const.PROJECT_PATH + "/" + itRatConfig.getWeiboPath();
            sce.getServletContext().setAttribute("managePath",managePath);
            sce.getServletContext().setAttribute("groupPath",Const.GROUP_PATH);
            sce.getServletContext().setAttribute("weiboPath",Const.WEIBO_PATH);
            IConfigService configService = SpringContextUtil.getBean("configService");
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
