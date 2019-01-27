package com.exampleproject.web.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BasicDAO {
    @Autowired
    SessionFactory sessionFactory;


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void persist(Object entity) {
        getSession().persist(entity);
    }

    protected void delete(Object entity) {
        getSession().delete(entity);
    }

    protected void update(Object entity) {
        getSession().update(entity);
    }
}
