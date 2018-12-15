package com.peng.itrat.core.handler;

import freemarker.core.Environment;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class DirectiveHandler {
    public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int FULL_DATE_LENGTH = 19;
    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int SHORT_DATE_LENGTH = 10;
    private Environment environment;
    private Map<String, TemplateModel> parameters;
    private TemplateModel[] loopVars;
    private TemplateDirectiveBody templateDirectiveBody;
    private Map<String, Object> map = new HashMap();

    public DirectiveHandler(Environment environment, Map<String, TemplateModel> parameters, TemplateModel[] loopVars, TemplateDirectiveBody templateDirectiveBody) {
        this.environment = environment;
        this.loopVars = loopVars;
        this.parameters = parameters;
        this.templateDirectiveBody = templateDirectiveBody;
    }

    public void render() throws IOException, TemplateException {
        Map reduceMap = this.reduce();
        if(null != this.templateDirectiveBody) {
            this.templateDirectiveBody.render(this.environment.getOut());
        }

        this.reduce(reduceMap);
    }

    public void renderIfNotNull(Object notEmptyObject) throws IOException, TemplateException {
        if(null != notEmptyObject) {
            this.render();
        }

    }

    public void print(String str) throws IOException, TemplateException {
        this.environment.getOut().append(str);
    }

    public DirectiveHandler put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    private Map<String, TemplateModel> reduce() throws TemplateModelException {
        HashMap reduceMap = new HashMap();

        String key;
        for(Iterator var2 = this.map.keySet().iterator(); var2.hasNext(); this.environment.setVariable(key, this.environment.getObjectWrapper().wrap(this.map.get(key)))) {
            key = (String)var2.next();
            TemplateModel value = this.environment.getVariable(key);
            if(null != value) {
                reduceMap.put(key, this.environment.getVariable(key));
            }
        }

        return reduceMap;
    }

    private void reduce(Map<String, TemplateModel> map) throws TemplateModelException {
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            this.environment.setVariable(key, (TemplateModel)map.get(key));
        }

    }

    public TemplateHashModel getMap(String name) throws TemplateModelException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        return (TemplateHashModel)(null == model?null:(model instanceof TemplateHashModelEx?(TemplateHashModelEx)model:(model instanceof TemplateHashModel?(TemplateHashModel)model:null)));
    }

    public String getString(String name, String defaultValue) throws TemplateException {
        String result = this.getString(name);
        return null == result?defaultValue:result;
    }

    public String getString(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        return null == model?null:(model instanceof TemplateScalarModel?((TemplateScalarModel)model).getAsString():(model instanceof TemplateNumberModel?((TemplateNumberModel)model).getAsNumber().toString():null));
    }

    public Integer getInteger(String name, int defaultValue) throws TemplateException {
        Integer result = this.getInteger(name);
        return null == result?Integer.valueOf(defaultValue):result;
    }

    public Integer getInteger(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateNumberModel) {
            return Integer.valueOf(((TemplateNumberModel)model).getAsNumber().intValue());
        } else if(model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel)model).getAsString();
            if(StringUtils.isBlank(s)) {
                return null;
            } else {
                try {
                    return Integer.valueOf(Integer.parseInt(s));
                } catch (NumberFormatException var5) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Short getShort(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateNumberModel) {
            return Short.valueOf(((TemplateNumberModel)model).getAsNumber().shortValue());
        } else if(model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel)model).getAsString();
            if(StringUtils.isBlank(s)) {
                return null;
            } else {
                try {
                    return Short.valueOf(Short.parseShort(s));
                } catch (NumberFormatException var5) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Long getLong(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateNumberModel) {
            return Long.valueOf(((TemplateNumberModel)model).getAsNumber().longValue());
        } else if(model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel)model).getAsString();
            if(StringUtils.isBlank(s)) {
                return null;
            } else {
                try {
                    return Long.valueOf(Long.parseLong(s));
                } catch (NumberFormatException var5) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Double getDouble(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateNumberModel) {
            return Double.valueOf(((TemplateNumberModel)model).getAsNumber().doubleValue());
        } else if(model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel)model).getAsString();
            if(StringUtils.isBlank(s)) {
                return null;
            } else {
                try {
                    return Double.valueOf(Double.parseDouble(s));
                } catch (NumberFormatException var5) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Integer[] getIntegerArray(String name) throws TemplateException {
        String[] arr = this.getStringArray(name);
        if(null != arr) {
            Integer[] ids = new Integer[arr.length];
            int i = 0;

            try {
                String[] e = arr;
                int var6 = arr.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String s = e[var7];
                    ids[i++] = Integer.valueOf(s);
                }

                return ids;
            } catch (NumberFormatException var9) {
                return null;
            }
        } else {
            return null;
        }
    }

    public Long[] getLongArray(String name) throws TemplateException {
        String[] arr = this.getStringArray(name);
        if(null != arr) {
            Long[] ids = new Long[arr.length];
            int i = 0;

            try {
                String[] e = arr;
                int var6 = arr.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String s = e[var7];
                    ids[i++] = Long.valueOf(s);
                }

                return ids;
            } catch (NumberFormatException var9) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String[] getStringArray(String name) throws TemplateException {
        String str = this.getString(name);
        return StringUtils.isBlank(str)?null:StringUtils.split(str, ',');
    }

    public Boolean getBoolean(String name, Boolean defaultValue) throws TemplateException {
        Boolean result = this.getBoolean(name);
        return null == result?defaultValue:result;
    }

    public Boolean getBoolean(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateBooleanModel) {
            return Boolean.valueOf(((TemplateBooleanModel)model).getAsBoolean());
        } else if(model instanceof TemplateNumberModel) {
            return Boolean.valueOf(0 != ((TemplateNumberModel)model).getAsNumber().intValue());
        } else if(model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel)model).getAsString();
            return !StringUtils.isNotBlank(s)?null:Boolean.valueOf(!"0".equals(s) && !"false".equalsIgnoreCase(s));
        } else {
            return null;
        }
    }

    public Date getDate(String name) throws TemplateException {
        TemplateModel model = (TemplateModel)this.parameters.get(name);
        if(null == model) {
            return null;
        } else if(model instanceof TemplateDateModel) {
            return ((TemplateDateModel)model).getAsDate();
        } else if(model instanceof TemplateScalarModel) {
            String temp = StringUtils.trimToEmpty(((TemplateScalarModel)model).getAsString());

            try {
                return 19 == temp.length()?FULL_DATE_FORMAT.parse(temp):(10 == temp.length()?SHORT_DATE_FORMAT.parse(temp):null);
            } catch (ParseException var5) {
                return null;
            }
        } else {
            return null;
        }
    }

    public Map<String, TemplateModel> getParameters() {
        return this.parameters;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public TemplateDirectiveBody getTemplateDirectiveBody() {
        return this.templateDirectiveBody;
    }

    public TemplateModel[] getLoopVars() {
        return this.loopVars;
    }

    public void setLoopVars(TemplateModel[] loopVars) {
        this.loopVars = loopVars;
    }
}