package cn.smart.caton.dao;

import cn.smart.caton.model.BaseEntity;

import java.util.List;

/**
 * Created by user on 2017/7/7.
 */
public interface BaseDao<T extends BaseEntity> {
    List<T> findAll();
    T findById(String id);
    int insert(T obj);
    int update(T obj);
    int delete(String id);
}
