package cn.smart.caton.dao;

import cn.smart.caton.model.BaseEntity;
import cn.smart.caton.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2017/7/7.
 */
public class SmartDaoSupport<T extends BaseEntity> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate(){
        return this.jdbcTemplate;
    }
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public SmartDaoSupport(){
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        persistentClass= (Class<T>)(parameterizedType.getActualTypeArguments()[0]);
    }

    public List<T> findAll(){
        return (List<T>)getJdbcTemplate().query(SQLUtil.queryAllSql(persistentClass),
                BeanPropertyRowMapper.newInstance(persistentClass));
    }

    public int insertOrUpdate(T obj){
        if(StringUtil.isEmpty(obj.getId())){
            return insert(obj);
        }
        return update(obj);
    }

    public int insert(T obj){
        Object[] insertValues = ArrayUtils.insert(0,BeanUtil.getValues(obj), UniqueKeyGenerator.getUniqueKeyId(),obj.getAddBy(), DateUtil.format(new Date(),DateUtil.YEAR_TO_SEC));
        return jdbcTemplate.update(SQLUtil.getInsertSql(persistentClass), insertValues);
    }
    public int update(T obj){
        Object[] insertValues = ArrayUtils.insert(0,BeanUtil.getValues(obj),obj.getUpdateBy(), DateUtil.format(new Date(),DateUtil.YEAR_TO_SEC));
        return jdbcTemplate.update(SQLUtil.getUpdateSql(persistentClass), ArrayUtils.add(insertValues,obj.getId()));
    }
    public T findById(String id){
        return (T)jdbcTemplate.queryForObject(SQLUtil.getQueryByIdSql(persistentClass),BeanPropertyRowMapper.newInstance(persistentClass),id);
    }

    public int delete(String id){
        return jdbcTemplate.update(SQLUtil.getDeleteSql(persistentClass),id);
    }

    public T queryForObject(String sql, RowMapper<T> rowMapper,Object... args){
        T obj = null;
        try{
            obj = jdbcTemplate.queryForObject(sql,rowMapper,args);
        }catch (Exception e){}
        return obj;
    }

    public List<T> queryForList(String sql,RowMapper<T> rowMapper,Object... args){
        List<T> list = null;
        try{
            list = jdbcTemplate.query(sql,rowMapper,args);
        } catch (Exception e){}
        return list;
    }

}
