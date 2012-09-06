package fi.ixonos.builder.web;

import fi.ixonos.builder.domain.Meego;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "meegoes", formBackingObject = Meego.class)
@RequestMapping("/meegoes")
@Controller
public class MeegoController {
}
