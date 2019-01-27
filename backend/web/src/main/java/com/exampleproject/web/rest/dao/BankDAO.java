package com.exampleproject.web.rest.dao;


import com.exampleproject.web.rest.entity.Bank;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Repository("bankDAO")
@Transactional
public class BankDAO extends BasicDAO implements Dao<Bank> {

    public List<Bank> getAllObjects() {
        Criteria criteria = getSession().createCriteria(Bank.class);
        List<Bank> list = criteria.list();
        System.out.println();
        return criteria.list();
    }

    public Bank getObject(BigInteger id) {
        Criteria criteria = getSession().createCriteria(Bank.class);
        criteria.add(Restrictions.eq("id",id));
        return (Bank) criteria.uniqueResult();
    }

    public String getEntityName() {
        return Bank.class.getSimpleName();
    }

    public void add(Bank entity) {
        persist(entity);
    }

    public int deleteById(int id) {
        Query query = getSession().createQuery("delete from Bank where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate();
    }


}
