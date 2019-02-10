package com.exampleproject.web.rest.dao;

import com.exampleproject.web.rest.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository("companyDAO")
@Transactional
public class CompanyDAO extends BasicDAO implements Dao<Company> {

    public List<Company> getAllObjects() {
        Criteria criteria = getSession().createCriteria(Company.class);
        return criteria.list();
    }

    public Company getObject(BigInteger id) {
        Criteria criteria = getSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("id",id));
        return (Company) criteria.uniqueResult();
    }

    public String getEntityName() {
        return Company.class.getSimpleName();
    }

    public void add(Company entity) {
        persist(entity);
    }

    public int deleteById(int id) {
        Query query = getSession().createQuery("delete from Company where id = :id");
        query.setInteger("id",id);
        return query.executeUpdate();
    }

    public void updateById(Company company) {
        update(company);
    }

    public Company getByName(String name) {
        Criteria criteria = getSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("companyName", name));
        return (Company) criteria.uniqueResult();
    }

    @Override
    public List<Company> getAccsById(Integer id) {
        return null;
    }


}
