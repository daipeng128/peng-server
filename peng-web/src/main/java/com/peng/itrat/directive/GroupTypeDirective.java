package com.peng.itrat.directive;

import com.peng.itrat.core.directive.BaseDirective;
import com.peng.itrat.core.handler.DirectiveHandler;
import com.peng.itrat.model.group.GroupType;
import com.peng.itrat.service.group.IGroupTypeService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/16 下午16:14
 */
@Component
public class GroupTypeDirective extends BaseDirective {

    @Resource
    private IGroupTypeService groupTypeService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        List<GroupType> list = groupTypeService.list();
        handler.put("groupTypeList", list).render();
    }

}
