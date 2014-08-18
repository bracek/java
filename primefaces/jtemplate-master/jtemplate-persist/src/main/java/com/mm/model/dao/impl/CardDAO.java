package com.mm.model.dao.impl;

import com.mm.model.dao.ICardDAO;
import com.mm.model.domain.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Card DAO
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */
@Named
public class CardDAO implements ICardDAO {
    @Inject
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCard(Card Card) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.save(Card);
        trans.commit();
    }

    public void deleteCard(Card Card) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.delete(Card);
        trans.commit();
    }

    public void updateCard(Card Card) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.update(Card);
        trans.commit();
    }

    public Card getCard(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        List<?> list = session
                .createQuery("from Card where id=?").setParameter(0, id)
                .list();

        trans.commit();
        return (Card) list.get(0);
    }

    public List<Card> getEntities() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Card> list = (List<Card>) session.createQuery("from Card").list();

        trans.commit();
        return list;
    }

}
