package com.globallogic.jackson.rest;

import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.SearchListResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * FundsController class will expose a series of RESTful endpoints
 */
@Controller
public class FundsController {


    @Autowired
    private View jsonView_i;


    private static final Logger logger_c = Logger
            .getLogger(FundsController.class);

    /**
     * Gets all lenka youtube.
     *
     * @return the funds
     */
    @RequestMapping(value = "/rest/youtube/", method = RequestMethod.GET)
    public ModelAndView getYoutube() {

        logger_c.debug("searching for lenka on youtube");
        final SearchListResponse resultItems = new Search().searchListResponse("lenka");
        return new ModelAndView(jsonView_i, resultItems);
    }



}
