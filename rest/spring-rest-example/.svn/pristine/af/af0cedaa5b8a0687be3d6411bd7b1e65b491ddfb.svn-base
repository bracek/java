package com.dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;

@Service
public class SimpleTradeService implements TradeService{

	@Autowired
	TradeRepository tradeRepository;	
	
	public SimpleTradeService()
	{
		super();
	}
	
	public SimpleTradeService(TradeRepository tradeRepository)
	{
		this.tradeRepository = tradeRepository;
	}
	
	@Override
	public Long createTrade(Trade t) {
		Long id = tradeRepository.createTrade(t);
		return id;
	}

	@Override
	public Trade getTradeById(Long id) {
		return tradeRepository.getTradeById(id);
	}

}
