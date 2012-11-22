package com.dbc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.dbc.model.Trade;
import com.thoughtworks.xstream.XStream;

public class TradeServiceIntegrationTest {
	
	private String endpoint = "http://localhost:8081/spring-rest-example";
	
	@Test
	public void testCreateTradeFromRestService() throws Exception {
		long id = (long) (Math.random()*100000);
		String tradeXml = createTrade(id);
		Trade t = getTradeFromXml(tradeXml);
		assertEquals(t.getId(), id);
	}

	@Test
	public void testGetTradeFromRestService() throws Exception {
		long id = (long) (Math.random()*100000);
		createTrade(id);
		String tradeXml = new RestTemplate()
				.getForObject(endpoint + "/find/trade/{id}",
						String.class, id);
		
		System.out.println(tradeXml);
		Trade trade = getTradeFromXml(tradeXml);
		assertEquals(trade.getId(), id);
	}

	private Trade getTradeFromXml(String trade) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Trade.class);
		Trade t = (Trade)xstream.fromXML(trade);
		return t;
	}	
	
	private String createTrade(Long id)
	{
		String tradeXml = new RestTemplate()
		.getForObject(
				endpoint + "/create/trade/{id}",
				String.class, id);
		return tradeXml;
	}
}
