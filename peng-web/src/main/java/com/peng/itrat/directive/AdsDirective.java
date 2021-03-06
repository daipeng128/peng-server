package com.peng.itrat.directive;

import com.peng.itrat.core.directive.BaseDirective;
import com.peng.itrat.core.handler.DirectiveHandler;
import com.peng.itrat.model.common.Ads;
import com.peng.itrat.service.common.IAdsService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zchuanzhao on 2017/09/08.
 */
@Component
public class AdsDirective extends BaseDirective {

    @Resource
    private IAdsService adsService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        Integer id = handler.getInteger("id",0);
        Ads ads = adsService.findById(id);
        handler.put("ad", ads).render();
    }

}
