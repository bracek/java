package com.ixonos.skillnet.logic.dao.impl;


import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.ixonos.skillnet.logic.bean.Book;
import com.ixonos.skillnet.logic.dao.BookDAO;

/**
 *
 * @author katrami
 */
@Repository("bookDAO")
public final class BookDaoImpl extends GenericDAOImpl<Book> implements BookDAO {

    @Autowired
    public BookDaoImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Book read(final Serializable id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Book> readAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
