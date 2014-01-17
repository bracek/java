package com.ixonos.skillnet.logic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.Message;
import com.ixonos.skillnet.logic.dao.MessageDAO;

/**
 *
 * @author katrami
 */
@Repository("messageDao")
public final class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {

    @Autowired
    public MessageDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
