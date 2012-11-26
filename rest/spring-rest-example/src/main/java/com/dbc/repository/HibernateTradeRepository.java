package com.dbc.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dbc.model.Trade;

public class HibernateTradeRepository extends HibernateDaoSupport implements TradeRepository {

    public Trade getTradeByReference(final String reference) {

        throw new RuntimeException();
    }

    public Long createTrade(final Trade trade) {
        return (Long) getHibernateTemplate().save(trade);
    }

    public Trade getTradeById(final Long id) {
        return getHibernateTemplate().get(Trade.class, id);
    }

}
