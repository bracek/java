package fi.ixonos.builder.web;

import fi.ixonos.builder.domain.Platform;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "platforms", formBackingObject = Platform.class)
@RequestMapping("/platforms")
@Controller
public class PlatformController {
}
