package com.exampleproject.web.rest.dao;



import com.exampleproject.web.rest.entity.BasicEntity;

import java.math.BigInteger;
import java.util.List;

public interface Dao<T extends BasicEntity> {
    List<T> getAllObjects();
    T getObject(BigInteger id);
    String getEntityName();
    void add(T entity);
    int deleteById(int id);
}
