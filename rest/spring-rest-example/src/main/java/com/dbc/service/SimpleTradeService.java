package com.dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;

@Service
public class SimpleTradeService implements TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public SimpleTradeService() {
        super();
    }

    public SimpleTradeService(final TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Long createTrade(final Trade t) {
        final Long id = tradeRepository.createTrade(t);
        return id;
    }

    public Trade getTradeById(final Long id) {
        return tradeRepository.getTradeById(id);
    }

}
