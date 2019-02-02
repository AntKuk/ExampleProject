package com.exampleproject.web.rest.dao;

import com.exampleproject.model.shared.UserDto;
import com.exampleproject.web.rest.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDao extends BasicDAO{
    public boolean isUser(UserDto user) {
        Criteria criteria = getSession().createCriteria(User.class);
        Criterion login = Restrictions.eq("login", user.getLogin());
        Criterion pwd = Restrictions.eq("pwd", user.getPwd());

        LogicalExpression andExp = Restrictions.and(login, pwd);

        criteria.add(andExp);

        User u = (User)criteria.uniqueResult();
        if(u != null) {
            return true;
        }
        return false;
    }
}
