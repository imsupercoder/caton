package cn.smart.caton.util;

import cn.smart.caton.annotation.Table;
import org.apache.commons.beanutils.BeanIntrospector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 2017/7/7.
 */
public class BeanUtil {

    private static ConcurrentHashMap<Class<?>,String> cachedTableNames = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<?>,String[]> cachedFieldNames = new ConcurrentHashMap<>();

    public static String getTableName(Object obj){
        return getTableName(obj.getClass());
    }

    public static String getTableName(Class clazz){
        String tableName = cachedTableNames.get(clazz);
        if(tableName!=null)
            return tableName;
        boolean hasTable = clazz.isAnnotationPresent(Table.class);
        if(hasTable){
            Table table = (Table)clazz.getAnnotation(Table.class);
            tableName = table.value();
            cachedTableNames.put(clazz,tableName);
            return tableName;
        }
        return null;
    }

    public static String[] getFields(Object obj){
        return getFields(obj.getClass());
    }
    public static String[] getFields(Class clazz){
        String[] fieldNames = cachedFieldNames.get(clazz);
        if(fieldNames!=null)
            return fieldNames;
        Field[] fields = clazz.getDeclaredFields();
        fieldNames = new String[fields.length];
        int index =0;
        for(Field field :fields){
            fieldNames[index++] = field.getName();
        }
        cachedFieldNames.put(clazz,fieldNames);
        return fieldNames;
    }
    public static Object[] getValues(Object obj){
        String[] fieldNames = getFields(obj);
        Object[] values = new Object[fieldNames.length];
        try{
            int index = 0;
            for(String fieldName : fieldNames){
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                values[index++] = field.get(obj);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return values;

    }
}
