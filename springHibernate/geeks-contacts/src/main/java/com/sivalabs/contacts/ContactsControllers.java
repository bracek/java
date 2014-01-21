package com.sivalabs.contacts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author SivaLabs
 */

@Controller
public class ContactsControllers {
	@Autowired
	private ContactsDAO contactsDAO;

	@Autowired
	private ContactFormValidator validator;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/searchContacts")
	public ModelAndView searchContacts(
			@RequestParam(required = false, defaultValue = "") final String name) {
		final ModelAndView mav = new ModelAndView("showContacts");
		final List<Contact> contacts = contactsDAO.searchContacts(name.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}

	@RequestMapping("/viewAllContacts")
	public ModelAndView getAllContacts() {
		final ModelAndView mav = new ModelAndView("showContacts");
		final List<Contact> contacts = contactsDAO.getAllContacts();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.GET)
	public ModelAndView newuserForm() {
		final ModelAndView mav = new ModelAndView("newContact");
		final Contact contact = new Contact();
		mav.getModelMap().put("newContact", contact);
		return mav;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String create(@ModelAttribute("newContact") final Contact contact,
			final BindingResult result, final SessionStatus status) {
		validator.validate(contact, result);
		if (result.hasErrors()) {
			return "newContact";
		}
		contactsDAO.save(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") final Integer id) {
		final ModelAndView mav = new ModelAndView("editContact");
		final Contact contact = contactsDAO.getById(id);
		mav.addObject("editContact", contact);
		return mav;
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public String update(final @ModelAttribute("editContact") final Contact contact,
			final BindingResult result, final SessionStatus status) {
		validator.validate(contact, result);
		if (result.hasErrors()) {
			return "editContact";
		}
		contactsDAO.update(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}

	@RequestMapping("deleteContact")
	public ModelAndView delete(@RequestParam("id") final Integer id) {
		final ModelAndView mav = new ModelAndView("redirect:viewAllContacts.do");
		contactsDAO.delete(id);
		return mav;
	}

}
