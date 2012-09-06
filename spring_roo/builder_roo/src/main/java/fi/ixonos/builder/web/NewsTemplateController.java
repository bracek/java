package fi.ixonos.builder.web;

import fi.ixonos.builder.domain.NewsTemplate;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "newstemplates", formBackingObject = NewsTemplate.class)
@RequestMapping("/newstemplates")
@Controller
public class NewsTemplateController {
}
