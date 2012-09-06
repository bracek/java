package fi.ixonos.projects.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import fi.ixonos.projects.logic.bean.Groups;
import fi.ixonos.projects.logic.dao.GroupsDAO;

/**
 *
 * @author magurja
 */
@Repository("groupsDAO")
public class GroupsDAOImpl extends GenericDAOImpl<Groups> implements GroupsDAO {

    @Autowired
    public GroupsDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
