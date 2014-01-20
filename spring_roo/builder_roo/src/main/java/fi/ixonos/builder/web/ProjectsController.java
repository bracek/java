package fi.ixonos.builder.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.ixonos.builder.domain.Projects;

@RooWebScaffold(path = "projectses", formBackingObject = Projects.class)
@RequestMapping("/projectses")
@Controller
public class ProjectsController {
	  
    @RequestMapping(method = RequestMethod.POST)
    public String create(final @Valid Projects projects,
final  BindingResult bindingResult,
final  Model uiModel,
final  HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("projects", projects);
            return "projectses/create";
        }
        uiModel.asMap().clear();
        projects.persist();
//       return "redirect:/projectses/" + encodeUrlPathSegment(projects.getId().toString(), httpServletRequest);
        return "redirect:/flow/";
	}

}
