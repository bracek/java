package com.mkyong.stock.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mkyong.stock.dao.StockDao;
import com.mkyong.stock.model.Stock;

public class StockDaoImpl extends HibernateDaoSupport implements StockDao{
	
	public void save(final Stock stock){
		getHibernateTemplate().save(stock);
	}
	
	public void update(final Stock stock){
		getHibernateTemplate().update(stock);
	}
	
	public void delete(final Stock stock){
		getHibernateTemplate().delete(stock);
	}
	
	public Stock findByStockCode(final String stockCode){
		List list = getHibernateTemplate().find("from Stock where stockCode=?",stockCode);
		return (Stock)list.get(0);
	}

}
