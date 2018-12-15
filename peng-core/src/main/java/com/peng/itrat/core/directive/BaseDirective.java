package com.peng.itrat.core.directive;

import com.peng.itrat.core.handler.DirectiveHandler;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;

public abstract class BaseDirective implements TemplateDirectiveModel {
    public BaseDirective() {
    }

    public void execute(Environment environment, Map parameters, TemplateModel[] loopVars, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        this.execute(new DirectiveHandler(environment, parameters, loopVars, templateDirectiveBody));
    }

    public abstract void execute(DirectiveHandler var1) throws TemplateException, IOException;
}