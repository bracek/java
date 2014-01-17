package com.dbc.repository;

import com.dbc.model.Trade;

public interface TradeRepository {

     Long createTrade(final Trade t);

     Trade getTradeById(final Long id);

     Trade getTradeByReference(final String reference);

}
