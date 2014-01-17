package com.dbc.service;

import com.dbc.model.Trade;

public interface TradeService {
    public Long createTrade(final Trade t);

    public Trade getTradeById(final Long id);
}
