package com.att.uchannels.model.dao.impl;

        import com.att.uchannels.model.dao.EntityDAO;
        import com.att.uchannels.domain.Entity;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Repository;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */
@Repository("entityDAO")
public class EntityDAOImpl extends GenericDAOImpl<Entity> implements EntityDAO {

    @Autowired
    public EntityDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}

