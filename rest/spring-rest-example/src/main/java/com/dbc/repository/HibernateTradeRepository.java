package com.dbc.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dbc.model.Trade;

public class HibernateTradeRepository  extends HibernateDaoSupport implements TradeRepository{
 
	@Override
	public Trade getTradeByReference(String reference) {

		throw new RuntimeException();
	}

	@Override
	public Long createTrade(Trade trade) {
		return (Long) getHibernateTemplate().save(trade);
	}

	@Override
	public Trade getTradeById(Long id) {
		return getHibernateTemplate().get(Trade.class, id);
	}

}
