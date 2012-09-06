package fi.ixonos.builder.web;

import fi.ixonos.builder.domain.Templates;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "templateses", formBackingObject = Templates.class)
@RequestMapping("/templateses")
@Controller
public class TemplatesController {
}
