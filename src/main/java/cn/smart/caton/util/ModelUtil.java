package cn.smart.caton.util;

/**
 * Created by wl on 2017/7/7.
 */
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.smart.caton.annotation.Column;
import cn.smart.caton.annotation.DBExclude;
import cn.smart.caton.annotation.Param;
import cn.smart.caton.annotation.Table;
import cn.smart.caton.model.User;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;



/**
 *
 * 封装了
 * Map<String,String> to bean
 * Map<String,Object> to bean
 * 及根据object获取相应的sql语句
 *
 * @author houzhaowei
 *
 */
public class ModelUtil{

    /**
     * 将List<Map<String, Object>> 对象构建 List<T> 对象
     * @param list 源数据
     * @param clz T.class
     * @return List<T>
     */
    public static <T> List<T> listMap2Object(List<Map<String, Object>> list, Class<T> clz) {

        // 生成集合
        List<T> result = new ArrayList<T>();
        // 遍历集合中的所有数据
        for (int i = 0; i < list.size(); i++) {
            try {
                //生成对象实历 将MAP中的所有参数封装到对象中
                T o = map2Object(list.get(i),clz);
                //把对象加入到集合中
                result.add(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 返回封装好的集合
        return result;
    }


    /**
     * 将 Map<String,Object> 构建成 T
     * @param map 源数据
     * @param clz T.class
     * @return  Map<String,Object>
     */
    public static <T> T map2Object(Map<String,Object> map,Class<T> clz) {
        if(map == null){
            return null;
        }
        T obj = null;

        try {
            obj = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 遍历所有名称
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            // 取得名称
            String name = it.next().toString().toLowerCase();
            Object oValue = map.get(name);
            if(null == oValue){
                continue;
            }
            // 取得值
            //String value = oValue.toString();

            try {
                //忽略属性大小写
                //获取所有私有成员变量
                Field[] fields = clz.getDeclaredFields();
                for(Field field : fields){

                    //如果标注为DBExclude，则忽略
                    if(field.isAnnotationPresent(DBExclude.class)){
                        DBExclude exclude = field.getAnnotation(DBExclude.class);
                        //获取元素值
                        boolean isExclude = exclude.value();
                        if(isExclude){
                            continue;
                        }
                    }

                    String fieldName=field.getName();

                    //解析注解,如果有注解，则把name改为fieldname
                    if(field.isAnnotationPresent(Column.class)){
                        Column colName = field.getAnnotation(Column.class);
                        if(name.equalsIgnoreCase(colName.value())){
                            name = fieldName;
                            break;
                        }
                    } else if (name.equalsIgnoreCase(fieldName)){
                        name = fieldName;
                        break;
                    }
                }

                // 取得值的类形
                Class<?> type = PropertyUtils.getPropertyType(obj, name);
                if (type != null) {
                    // 设置参数
                    PropertyUtils.setProperty(obj, name,ConvertUtils.convert(oValue, type));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return obj;

    }

    /**
     * 将 Map<String,String> 构建成 T
     * @param map 源数据
     * @param clz T.class
     * @return  Map<String,String>
     */
    public static <T> T map2ObjectStr(Map<String,String> map,Class<T> clz) {

        T obj = null;

        try {
            obj = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 遍历所有名称
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            // 取得名称
            String name = it.next().toString();
            String value="";
            try {
                value = URLDecoder.decode(map.get(name),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(null == value || value.equals("")){
                continue;
            }
            try {
                //忽略属性大小写
                Field[] fields = clz.getDeclaredFields();
                for(Field field : fields){
                    String fieldName = field.getName();

                    //如果加上param注解了，并且注解的value和传入的参数相等
                    if(field.isAnnotationPresent(Param.class)){
                        Param param =  field.getAnnotation(Param.class);
                        if(name.equalsIgnoreCase(param.value())){
                            name = fieldName;
                            break;
                        }
                    } else if(name.equalsIgnoreCase(fieldName)){
                        name = fieldName;
                        break;
                    }
                }

                // 取得值的类形
                Class<?> type = PropertyUtils.getPropertyType(obj, name);
                if (type != null) {
                    // 设置参数
                    PropertyUtils.setProperty(obj, name,ConvertUtils.convert(value, type));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return obj;
    }

    /**
     * 将 bean 封装成 sql 语句
     * bean 对应的class 必须使用注解 @Table(name="xxxxx")
     * @param sqlType sql 类型
     * @param obj 需要的对象
     * @return sql 语句
     */
    public static <T> String getSqlByObject(SqlTypes sqlType, T obj) {
        Class<?> clz = obj.getClass();
        //如果使用了注解，则获取注解的tablename
        if(clz.isAnnotationPresent(Table.class)){
            Table table = (Table) clz.getAnnotation(Table.class);
            String tableName = table.value();
            return getSqlByObject(sqlType,obj,tableName);
        }else{
            System.err.println("clz is not using annotation ,plz call anothor methon to input tablename or add annotation 'Table' to clz");
            return null;
        }
    }

    /**
     * 将 bean 封装成 sql 语句
     * @param sqlType sql 类型
     * @param obj 需要的对象
     * @param tableName 表名
     * @return sql语句
     */
    public static <T> String getSqlByObject(SqlTypes sqlType , T obj , String tableName){
        Class<?> clz = obj.getClass();
        String[] intTypes = {"int","java.lang.Integer","short","java.lang.Short","long","java.lang.Long","BigDecimal","java.math.BigDecimal"};
        String[] dateFields = {"Date","java.util.Date"};
        StringBuffer sql = new StringBuffer("");
        StringBuffer cols = new StringBuffer("");
        StringBuffer values = new StringBuffer("");
        //获取所有私有成员变量
        Field[] fields = clz.getDeclaredFields();
        if (null == fields || fields.length == 0){
            return null;
        }

        if(sqlType == SqlTypes.INSERT){
            sql.append("insert into ").append(tableName).append(" (");
            int index = 1;
            for(Field field : fields){
                //如果标注为DBExclude，则忽略
                if(field.isAnnotationPresent(DBExclude.class)){
                    DBExclude exclude = field.getAnnotation(DBExclude.class);
                    //获取元素值
                    boolean isExclude = exclude.value();
                    if(isExclude){
                        continue;
                    }
                }

                //设置成员变量可访问
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> type = field.getType();

                Object value = null;

                //获取value
                try{
                    PropertyDescriptor pd = new PropertyDescriptor(fieldName,clz);
                    Method getMethod = pd.getReadMethod();//获得get方法
                    value = getMethod.invoke(obj);
                } catch (Exception e){
                    e.printStackTrace();
                }

                //如果有注解，则使用注解的字段名，没有注解，则使用字段名
                if(field.isAnnotationPresent(Column.class)){
                    Column column = field.getAnnotation(Column.class);
                    //获取元素值
                    fieldName = column.value();
                    if(fieldName.equals("")){
                        continue;
                    }
                }

                //如果没有值，则不需要插入此字段，只需使用db默认值即可
                if(null == value || "".equals(value)){
                    continue;
                }

                boolean isNumber = false;//是否是数字类型
                boolean isDate = false;//是否是date类型
                if (StringUtil.contains(intTypes, type.getName())){
                    isNumber = true;
                } else if (StringUtil.contains(dateFields, type.getName())){
                    isDate = true;
                }

                if (index == 1){
                    cols.append(fieldName);
                    if(isNumber){
                        values.append(value);
                    } else if (isDate){
                        values.append("to_date('"+DateUtil.format((Date)value,DateUtil.YEAR_TO_SEC)+"','yyyy-mm-dd hh24:mi:ss')");
                    } else {
                        values.append("'").append(value).append("'");
                    }
                } else {
                    cols.append(",").append(fieldName);
                    if(isNumber){
                        values.append(",").append(value);
                    } else if (isDate){
                        values.append(",to_date('"+DateUtil.format((Date)value,DateUtil.YEAR_TO_SEC)+"','yyyy-mm-dd hh24:mi:ss')");
                    } else{
                        values.append(",'").append(value).append("'");
                    }

                }
                index ++;
            }

            sql.append(cols.toString()).append(") values (").append(values).append(")");
        }

        return sql.toString();
    }




    public enum SqlTypes{
        INSERT,UPDATE,DELETE,QUERY
    }

    public static void main(String[] args) {
        System.out.println(getSqlByObject(SqlTypes.QUERY,new User()));
        System.out.println(getSqlByObject(SqlTypes.INSERT,new User()));
        System.out.println(getSqlByObject(SqlTypes.UPDATE,new User()));
        System.out.println(getSqlByObject(SqlTypes.DELETE,new User()));
    }

}