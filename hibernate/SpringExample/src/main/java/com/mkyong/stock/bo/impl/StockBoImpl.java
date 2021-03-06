package com.mkyong.stock.bo.impl;

import com.mkyong.stock.bo.StockBo;
import com.mkyong.stock.dao.StockDao;
import com.mkyong.stock.model.Stock;

public class StockBoImpl implements StockBo{
	
	StockDao stockDao;
	
	public void setStockDao(final StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public void save(final Stock stock){
		stockDao.save(stock);
	}
	
	public void update(final Stock stock){
		stockDao.update(stock);
	}
	
	public void delete(final Stock stock){
		stockDao.delete(stock);
	}
	
	public Stock findByStockCode(final String stockCode){
		return stockDao.findByStockCode(stockCode);
	}
}
