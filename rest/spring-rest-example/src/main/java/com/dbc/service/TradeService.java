package com.dbc.service;

import com.dbc.model.Trade;

public interface TradeService {
     Long createTrade(final Trade t);

     Trade getTradeById(final Long id);
}
