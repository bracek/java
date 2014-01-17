package com.blog.samples.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getMovie(@PathVariable final String name, final ModelMap model) {

        model.addAttribute("movie", name);
        return "accounts";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefaultMovie(final ModelMap model) {

        model.addAttribute("movie", "this is default movie");
        return "accounts";

    }
}
