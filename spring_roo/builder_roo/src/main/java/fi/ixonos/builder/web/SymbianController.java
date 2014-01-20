package fi.ixonos.builder.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import fi.ixonos.builder.domain.Symbian;

@RooWebScaffold(path = "symbians", formBackingObject = Symbian.class)
@RequestMapping("/symbians")
@Controller
public class SymbianController {

	private static final Log log = LogFactory.getLog(SymbianController.class);
	private static final String imageUrl = "http://localhost:8080/builder/symbians/showdoc/";

	@RequestMapping(method = RequestMethod.GET)
	public String list(final 			@RequestParam(value = "page",
final  required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();

			List<Symbian> symbians = Symbian.findSymbianEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
			for (int i = 0; i < symbians.size(); i++) {
				Symbian symb = symbians.get(i); 
				symb.setUrl(imageUrl + symb.getId());
			}
			
			uiModel.addAttribute("symbians", symbians);
			float nrOfPages = (float) Symbian.countSymbians() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("symbians", Symbian.findAllSymbians());
		}
		return "symbians/list";
	}

	@InitBinder
	protected void initBinder(final HttpServletRequest request,
final 			ServletRequestDataBinder binder) throws ServletException {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "savedoc", method = RequestMethod.POST)
	public String createdoc(final @Valid Symbian symbian,
final  BindingResult result,
final 			Model model,
final  @RequestParam("content") MultipartFile content,
			HttpServletRequest request) {

		// symbian.setContentType(content.getContentType());
		symbian.setFilename(content.getOriginalFilename());
		// symbian.setSize(content.getSize());

		log.debug("Document: ");
		log.debug("Name: " + content.getOriginalFilename());
		// log.debug("Description: "+document.getDescription());
		log.debug("File: " + content.getName());
		log.debug("Type: " + content.getContentType());

		if (result.hasErrors()) {
			model.addAttribute("symbian", symbian);
			return "symbians/create";
		}
		symbian.persist();

		return "redirect:/symbians?page=1&size=10";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(final @PathVariable("id") Long id,
final  Model model) {
		Symbian doc = Symbian.findSymbian(id);
		doc.setUrl("http://localhost:8080/builder/symbians/showdoc/" + id);
		model.addAttribute("symbian", Symbian.findSymbian(id));
		model.addAttribute("itemId", id);
		return "symbians/show";
	}

	@RequestMapping(value = "/showdoc/{id}", method = RequestMethod.GET)
	public String showdoc(final @PathVariable("id") Long id,
			HttpServletResponse response, Model model) {

		Symbian doc = Symbian.findSymbian(id);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ doc.getFilename() + "\"");

			OutputStream out = response.getOutputStream();
			IOUtils.copy(new ByteArrayInputStream(doc.getContent()), out);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(final @PathVariable("id") Long id,
final  Model model) {
		Symbian symbian = Symbian.findSymbian(id);
		symbian.setUrl("http://localhost:8080/builder/symbians/showdoc/" + id);
		model.addAttribute("symbian", symbian);
		return "symbians/update";
	}
}
