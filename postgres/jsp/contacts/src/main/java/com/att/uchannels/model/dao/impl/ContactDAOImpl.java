package com.att.uchannels.model.dao.impl;

import com.att.uchannels.model.dao.ContactDAO;
import com.att.uchannels.domain.Contact;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */
@Repository("contactDAO")
@Transactional
public class ContactDAOImpl extends GenericDAOImpl<Contact> implements ContactDAO {

    @Autowired
    public ContactDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

//    public Contact getById(final int id) {
//        return (Contact) getSession().get(Contact.class,
//                id);
//    }
}

