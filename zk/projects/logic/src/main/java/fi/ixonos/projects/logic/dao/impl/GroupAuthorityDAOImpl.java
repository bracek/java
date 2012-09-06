package fi.ixonos.projects.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import fi.ixonos.projects.logic.bean.GroupAuthority;
import fi.ixonos.projects.logic.dao.GroupAuthorityDAO;

/**
 *
 * @author magurja
 */
@Repository("groupAuthorityDAO")
public class GroupAuthorityDAOImpl extends GenericDAOImpl<GroupAuthority> implements GroupAuthorityDAO {

    @Autowired
    public GroupAuthorityDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
