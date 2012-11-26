package com.blog.samples.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.blog.samples.domain.Fund;

@Service
public class FundService {

    private static final Logger logger_c = Logger.getLogger(FundService.class);

    /**
     * Get the fund by id.
     * 
     * @param fundId_p
     *            the fund id_p
     * @return the fund by id
     */
    public Fund getFundById(final String fundId_p) {
        final Fund fund = new Fund();

        fund.setFundId(fundId_p);
        fund.setFundDescription("High Risk Equity Fund");
        fund.setBidPrice(1.2);
        fund.setOfferPrice(3.3);
        fund.setLastUpdated(new Date());

        return fund;
    }

    /**
     * Gets all funds.
     * 
     * @return the all funds
     */
    public List<Fund> getAllFunds() {
        final List<Fund> funds = new ArrayList<Fund>();

        for (int i = 0; i < 10; i++) {
            final Fund fund = new Fund();

            fund.setFundId("12345" + i);
            fund.setFundDescription("High Risk Equity Fund " + (i + 1));
            fund.setBidPrice(26.8 + (Math.random() * 10));
            fund.setOfferPrice(27.4 + (Math.random() * 10));
            fund.setLastUpdated(new Date());

            funds.add(fund);
        }
        return funds;
    }

    /**
     * Creates the fund.
     * 
     * @param fund_p
     *            the fund_p
     * @return the fund
     */
    public Fund createFund(final Fund fund_p) {

        logger_c.debug("Persisting fund in database " + fund_p.toString());

        /* set id and timestamp */
        fund_p.setFundId("");
        fund_p.setLastUpdated(new Date());

        return fund_p;
    }

    /**
     * Update fund.
     * 
     * @param fund_p
     *            the fund_p
     * @return the fund
     */
    public Fund updateFund(final Fund fund_p) {

        logger_c.debug("Updating fund in database " + fund_p.toString());

        /* set timestamp */
        fund_p.setLastUpdated(new Date());

        return fund_p;
    }

    /**
     * Delete fund.
     * 
     * @param fundId_p
     *            the fund id_p
     */
    public void deleteFund(final String fundId_p) {
        logger_c.debug("Deleting fund from database " + fundId_p.toString());
    }
}
