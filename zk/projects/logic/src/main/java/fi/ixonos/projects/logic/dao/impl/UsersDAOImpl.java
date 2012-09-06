package fi.ixonos.projects.logic.dao.impl;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.UsersDAO;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author katrami
 * @date Oct 11, 2010
 */
@Repository("usersDAO")
public class UsersDAOImpl extends GenericDAOImpl<Users> implements UsersDAO {

    @Autowired
    public UsersDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
