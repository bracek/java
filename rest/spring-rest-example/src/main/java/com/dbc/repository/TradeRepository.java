package com.dbc.repository;

import com.dbc.model.Trade;

public interface TradeRepository {

    public Long createTrade(final Trade t);

    public Trade getTradeById(final Long id);

    public Trade getTradeByReference(final String reference);

}
