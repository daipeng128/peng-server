package com.peng.itrat.core.provider;

import com.peng.itrat.core.annotation.Column;
import com.peng.itrat.core.annotation.Id;
import com.peng.itrat.core.annotation.Table;
import com.peng.itrat.core.conditions.SqlWrapper;
import com.peng.itrat.core.enums.FillTime;
import com.peng.itrat.core.enums.IdType;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.utils.StringUtils;
import com.peng.itrat.core.utils.UuidUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SqlProvider<T> {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LIST_ALL = "listAll";
    public static final String LIST_BY_PAGE = "listByPage";
    public static final String GET_BY_ID = "getById";
    public static final String DELETE_BY_ID = "deleteById";
    public static final String DELETE_OBJ = "deleteObj";
    public static final String SAVE_OBJ = "saveObj";
    public static final String UPDATE_OBJ = "updateObj";
    public static final String FIND_BY_COLUMNS = "findByColumns";

    public SqlProvider() {
    }

    public String listAll(SqlWrapper<T> sqlWrapper) {
        SQL sql = new SQL();
        ((SQL)sql.SELECT("*")).FROM(this.getTableName(sqlWrapper.getEntityClass()));
        if(StringUtils.isNotBlank(sqlWrapper.getOrderBy())) {
            sql.ORDER_BY(sqlWrapper.getOrderBy());
        }

        return sql.toString();
    }

    public String listByPage(@Param("page") Page page, @Param("sqlWrapper") SqlWrapper<T> sqlWrapper) {
        SQL sql = new SQL();
        ((SQL)sql.SELECT("*")).FROM(this.getTableName(sqlWrapper.getEntityClass()));
        if(StringUtils.isNotBlank(sqlWrapper.getOrderBy())) {
            sql.ORDER_BY(sqlWrapper.getOrderBy());
        }

        return sql.toString();
    }

    public String getById(@Param("id") Integer id, @Param("c") Class<?> c) {
        SQL sql = new SQL();
        ((SQL)((SQL)sql.SELECT("*")).FROM(this.getTableName(c))).WHERE(this.getIdName(c) + "=#{id}");
        return sql.toString();
    }

    public String deleteById(@Param("id") Integer id, @Param("c") Class<?> c) {
        SQL sql = new SQL();
        ((SQL)sql.DELETE_FROM(this.getTableName(c))).WHERE(this.getIdName(c) + "=#{id}");
        return sql.toString();
    }

    public String deleteObj(T t) {
        SQL sql = new SQL();
        ((SQL)sql.DELETE_FROM(this.getTableName(t.getClass()))).WHERE(this.getIdName(t.getClass()) + "=#{" + this.getIdFieldName(t.getClass()) + "}");
        return sql.toString();
    }

    public String saveObj(T t) {
        SQL sql = new SQL();
        sql.INSERT_INTO(this.getTableName(t.getClass()));
        Field[] field = t.getClass().getDeclaredFields();
        Field[] var4 = field;
        int var5 = field.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field nameField = var4[var6];
            Column column = (Column)nameField.getAnnotation(Column.class);
            Id id = (Id)nameField.getAnnotation(Id.class);
            if(null != column || null != id) {
                String name = nameField.getName();
                Object val = this.getFieldValue(t, name);
                boolean isFill = false;
                if(null == val) {
                    String fieldType = nameField.getType().toString();
                    if(fieldType.equalsIgnoreCase("class java.util.Date")) {
                        FillTime now = column.currTime();
                        if(FillTime.INSERT == now) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            val = sdf.format(new Date());
                            isFill = true;
                        }
                    }
                }

                if(null != val) {
                    if(null == id) {
                        sql.INTO_COLUMNS(new String[]{column.value()});
                        if(isFill) {
                            sql.INTO_VALUES(new String[]{"\'" + String.valueOf(val) + "\'"});
                        } else {
                            sql.INTO_VALUES(new String[]{"#{" + name + "}"});
                        }
                    } else if(id.type() != IdType.AUTO && id.type() != IdType.NONE) {
                        sql.INTO_COLUMNS(new String[]{id.value()});
                        if(id.type() == IdType.UUID) {
                            sql.INTO_VALUES(new String[]{"\'" + UuidUtil.getUnid() + "\'"});
                        } else {
                            sql.INTO_VALUES(new String[]{"#{" + name + "}"});
                        }
                    }
                }
            }
        }

        return sql.toString();
    }

    public String updateObj(T t) {
        SQL sql = new SQL();
        sql.UPDATE(this.getTableName(t.getClass()));
        Field[] fields = t.getClass().getDeclaredFields();
        Id id = null;
        String idName = null;
        Field[] var6 = fields;
        int var7 = fields.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Field field = var6[var8];
            if(null == id) {
                id = (Id)field.getAnnotation(Id.class);
                if(null != id) {
                    idName = field.getName();
                }
            }

            Column column = (Column)field.getAnnotation(Column.class);
            if(null != column) {
                Object val = this.getFieldValue(t, field.getName());
                if(null != val) {
                    sql.SET(column.value() + "=#{" + field.getName() + "}");
                }
            }
        }

        sql.WHERE(id.value() + "=#{" + idName + "}");
        return sql.toString();
    }

    public String findByColumns(Map<String, Object> params, Class<?> c) {
        SQL sql = new SQL();
        Iterator iterator = params.entrySet().iterator();
        int i = 0;
        ((SQL)sql.SELECT("*")).FROM(this.getTableName(c));

        for(; iterator.hasNext(); ++i) {
            String column = (String)((Entry)iterator.next()).getKey();
            if(!column.startsWith("param")) {
                sql.WHERE(column + "=#{" + column + "}");
            }
        }

        return sql.toString();
    }

    private String getTableName(Class<?> c) {
        Table annotation = (Table)c.getAnnotation(Table.class);
        String tableName = annotation.value();
        return tableName;
    }

    private String getIdName(Class<?> c) {
        String idName = null;
        Field[] fields = c.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            Id annotation = (Id)field.getAnnotation(Id.class);
            if(null != annotation) {
                idName = annotation.value();
                break;
            }
        }

        return idName;
    }

    private String getIdFieldName(Class<?> c) {
        String idFieldName = null;
        Field[] fields = c.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            Id annotation = (Id)field.getAnnotation(Id.class);
            if(null != annotation) {
                idFieldName = field.getName();
                break;
            }
        }

        return idFieldName;
    }

    private Object getFieldValue(T t, String field) {
        String firstLetter = field.substring(0, 1).toUpperCase();
        String getMethodName = "get" + firstLetter + field.substring(1);
        Method getMethod = null;

        try {
            getMethod = t.getClass().getMethod(getMethodName, new Class[0]);
        } catch (NoSuchMethodException var10) {
            var10.printStackTrace();
        }

        Object value = null;

        try {
            value = getMethod.invoke(t, new Object[0]);
        } catch (IllegalAccessException var8) {
            var8.printStackTrace();
        } catch (InvocationTargetException var9) {
            var9.printStackTrace();
        }

        return value;
    }
}
