package com.springsource.roo.pizzashop.web;

import com.springsource.roo.pizzashop.domain.Symbian_general;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/symbian_generals")
@Controller
@RooWebScaffold(path = "symbian_generals", formBackingObject = Symbian_general.class)
public class Symbian_generalController {
}
