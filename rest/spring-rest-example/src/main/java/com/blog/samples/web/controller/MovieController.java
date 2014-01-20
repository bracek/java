package com.blog.samples.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getMovie(final @PathVariable String name,
final  ModelMap model) {

        model.addAttribute("movie", name);
        return "list";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefaultMovie(final ModelMap model) {

        model.addAttribute("movie", "this is default movie");
        return "list";

    }
}
