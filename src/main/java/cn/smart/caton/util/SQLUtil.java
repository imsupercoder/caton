package cn.smart.caton.util;

/**
 * Created by user on 2017/7/7.
 */
public class SQLUtil {

    public static String queryAllSql(Class clazz){
        return "select * from "+BeanUtil.getTableName(clazz);
    }
    public static String getDeleteSql(Class clazz){
        return "delete from "+BeanUtil.getTableName(clazz) +" where id=?";
    }
    public static String getInsertSql(Class clazz){
        String names = "ID,ADDBY,ADDTIME,";
        String placeholder = "?,?,?,";
        String[] fields = BeanUtil.getFields(clazz);
        for(String field : fields){
            names+=field.toUpperCase()+",";
            placeholder+="?,";
        }
        names = names.substring(0,names.length()-1);
        placeholder = placeholder.substring(0,placeholder.length()-1);
        return "insert into "+BeanUtil.getTableName(clazz)+" ("+names+") values ("+placeholder+")";
    }
    public static String getQueryByIdSql(Class clazz){
        return queryAllSql(clazz)+" where id= ?";
    }
    public static String getUpdateSql(Class clazz){
        String params = "UPDATEBY=?,UPDATETIME=?,";
        String[] fields = BeanUtil.getFields(clazz);
        for(String field : fields){
            params+=field.toUpperCase()+"=?,";
        }
        params = params.substring(0,params.length()-1);
        return "update "+BeanUtil.getTableName(clazz)+" set "+params+" where id=?";
    }
}
