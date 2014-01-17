package fi.ixonos.builder.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.ixonos.builder.domain.Meego;
import fi.ixonos.builder.domain.Projects;
import fi.ixonos.builder.domain.Symbian;

@RequestMapping("/flow")
@Controller
public class FlowController {

	@RequestMapping(method = RequestMethod.GET)
	public String getProjects(final Model uiModel) {

		List<Projects> projects = Projects.findAllProjectses();
		for (int i = 0; i < projects.size(); i++) {
			Projects symb = projects.get(i);
			symb.setMeego(symb.getMeego());		
		}

		uiModel.addAttribute("projectses", projects);
		return "flow/state1";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String setProject(final @Valid Projects projects,final 			BindingResult bindingResult,final  Model uiModel,final 			HttpServletRequest httpServletRequest) {

		boolean hasID = false;
		hasID = uiModel.containsAttribute("hiddenId1");

		boolean hasID2 = false;
		hasID2 = uiModel.containsAttribute("hiddenId");

		// Projects pr1 = Projects.findProjects(new Long(1));
		// uiModel.addAttribute("projects", Projects.findAllProjectses());
		// uiModel.addAttribute("projects", pr1);
		uiModel.addAttribute("symbian", new Symbian());
		// return "symbians/create";
		return "symbians/create";
	}

	//
	// @RequestMapping(params = "page1", method = RequestMethod.POST)
	// public String setProject1(@RequestParam(value = "page1", required =
	// false) String activationKey, @Valid Projects projects,
	// BindingResult bindingResult, Model uiModel,
	// HttpServletRequest httpServletRequest) {
	//
	// boolean hasID =false;
	// hasID=uiModel.containsAttribute("hiddenId1");
	//
	// Object x = httpServletRequest.getAttribute("hiddenId1");
	//
	//
	//
	//
	// // uiModel.addAttribute("projectses", Projects.findAllProjectses());
	// uiModel.addAttribute("projects", Projects.findProjects(new Long(1)));
	// uiModel.addAttribute("symbian", new Symbian());
	// return "symbians/create";
	// }
}
