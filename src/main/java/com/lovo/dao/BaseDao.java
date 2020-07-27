package com.lovo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 基础dao层
 * BaseDao里面封装所有Dao类的基本实现
 * @param <E> 业务实体
 * @param <K> 一般指代主键id
 */
public interface BaseDao<E,K extends Serializable> {
    /**
     * 保存新增
     * @param entity 业务实体类
     * @return 新增成功的id
     */
    public K save(E entity);

    /**
     * 删除
     * @param entity 业务实体
     * @return 成功true，失败false
     */
    public boolean delete(E entity);

    /**
     * 根据主键id删除数据
     * @param id 主键id
     * @return 成功true，失败false
     */
    public boolean deleteById(K id);

    /**
     * 根据主键id查询业务实体
     * @param id 主键id
     * @return 业务实体或者null
     */
    public E findById(K id);

    /**
     * 查询所有
     * @return 返回业务实体的集合
     */
    public List<E> findAll();

    /**
     * 更新数据
     * @param entity  业务实体
     * @return 几行受影响
     */
    public K update(E entity);
}
