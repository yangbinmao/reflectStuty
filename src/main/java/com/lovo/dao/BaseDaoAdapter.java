package com.lovo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YBM on 2020/7/27 21:36
 */

/**
 * 基础basedao的中转类
 * @param <E>
 * @param <K>
 */
public class  BaseDaoAdapter<E,K extends Serializable> implements BaseDao<E,K>{
    @Override
    public K save(E entity) {
        return null;
    }

    @Override
    public boolean delete(E entity) {
        return false;
    }

    @Override
    public boolean deleteById(K id) {
        return false;
    }

    @Override
    public E findById(K id) {
        return null;
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public K update(E entity) {
        return null;
    }
}
