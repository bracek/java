package com.blog.samples.webservices.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funds")
public class FundController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getMovie(@PathVariable final String name,
 final ModelMap model) {

        model.addAttribute("movie", name);
        return "list";

    }

    /*
     * @RequestMapping(final value = "/funds/",final  method = RequestMethod.GET) public ModelAndView getFunds() { List<Fund> funds = null;
     * 
     * try { funds = fundService_i.getAllFunds(); } catch (final Exception e) { final String sMessage = "Error getting all funds. [%1$s]"; return
     * createErrorResponse(String.format(sMessage, e.toString())); }
     * 
     * logger_c.debug("Returing Funds: " + funds.toString()); return new ModelAndView(jsonView_i, DATA_FIELD, funds); }
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefaultMovie(final ModelMap model) {

        model.addAttribute("movie", "this is default funds");
        return "list";

    }

    private ModelAndView createErrorResponse(final String format) {
        // TODO Auto-generated method stub
        return null;
    }

}
