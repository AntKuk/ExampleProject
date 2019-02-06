package com.exampleproject.web.rest.dao;

import com.exampleproject.web.rest.entity.Transact;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository("transactionDAO")
@Transactional
public class TransactionDAO extends BasicDAO implements Dao<Transact> {
 /*   public List<Transact> getAllTransactions() {
        Criteria criteria = getSession().createCriteria(Transact.class);
        List<Transact> list = criteria.list();
        System.out.println();
        return criteria.list();
    }
*/
    public List<Transact> getAllObjects() {
        Criteria criteria = getSession().createCriteria(Transact.class);
        return criteria.list();
    }

    public Transact getObject(BigInteger id) {
        Criteria criteria = getSession().createCriteria(Transact.class);
        criteria.add(Restrictions.eq("id",id));
        return (Transact) criteria.uniqueResult();
    }

    public String getEntityName() {
        return Transact.class.getSimpleName();
    }

    public void add(Transact entity) {
        persist(entity);
    }

    public int deleteById(int id) {
        Query query = getSession().createQuery("delete from Transact where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate();
    }

    @Override
    public void updateById(Transact entity) {

    }

    @Override
    public Transact getByName(String name) {
        return null;
    }

    @Override
    public List<Transact> getAccsById(Integer id) {
        return null;
    }


}
