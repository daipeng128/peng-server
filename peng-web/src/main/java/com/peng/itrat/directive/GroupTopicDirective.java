package com.peng.itrat.directive;

import com.peng.itrat.core.directive.BaseDirective;
import com.peng.itrat.core.handler.DirectiveHandler;
import com.peng.itrat.model.group.GroupTopic;
import com.peng.itrat.service.group.IGroupTopicService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/18.
 */
@Component
public class GroupTopicDirective extends BaseDirective {
    @Resource
    private IGroupTopicService groupTopicService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        int gid = handler.getInteger("gid",0);
        int num = handler.getInteger("num",0);
        String sort = handler.getString("sort","id");
        int day = handler.getInteger("day",0);
        int thumbnail = handler.getInteger("thumbnail",0);
        List<GroupTopic> list = groupTopicService.listByCustom(gid,sort,num,day,thumbnail);
        handler.put("groupTopicList", list).render();
    }

}
