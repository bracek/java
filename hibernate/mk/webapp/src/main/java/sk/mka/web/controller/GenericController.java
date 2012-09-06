package sk.mka.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Generic controller.
 */
@Controller
public class GenericController {

    /**
     * Necessary for processing of static pages or 
     * ones not requiring initialization.
     */
    @RequestMapping(value = "/**/*.do")
    public void genericPage() {
    }
}
