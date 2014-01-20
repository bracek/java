package com.mkyong.stock.model;

import java.io.Serializable;

public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long stockId;
	private String stockCode;
	private String stockName;

	public Stock() {
	}

	public Stock(final String stockCode,
final  String stockName) {
		this.stockCode = stockCode;
		this.stockName = stockName;
	}

	public Long getStockId() {
		return this.stockId;
	}

	public void setStockId(final Long stockId) {
		this.stockId = stockId;
	}

	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(final String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(final String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "Stock [stockCode=" + stockCode + ", stockId=" + stockId
				+ ", stockName=" + stockName + "]";
	}

	
}
