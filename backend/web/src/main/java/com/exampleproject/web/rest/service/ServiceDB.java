package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.BasicDto;

import java.math.BigInteger;
import java.util.List;

public interface ServiceDB<T extends BasicDto> {
    List<T> getAll();
    String getEntityName();
    void add(T entity);
    void deleteById(int id);
    void updateById(T entity);
}
