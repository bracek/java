package com.att.uchannels.controllers.web;

import com.att.uchannels.domain.Contact;
import com.att.uchannels.model.dao.ContactDAO;
import com.att.uchannels.validator.ContactFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Miroslav Katrak
 */

@Controller
public class ContactsControllers {

    @Autowired
    private ContactDAO contactsDAO;

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
//        final List<Contact> contacts = contactsDAO.searchContacts(name.trim());
        Contact contact = new Contact();
        contact.setName(name.trim());
        final List<Contact> contacts = contactsDAO.readByCriteria(contact);
        mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
        return mav;
    }

    @RequestMapping("/viewAllContacts")
    public ModelAndView getAllContacts() {
        final ModelAndView mav = new ModelAndView("showContacts");
//        final List<Contact> contacts = contactsDAO.getAllContacts();
        final List<Contact> contacts = contactsDAO.readAll();
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
//        contactsDAO.save(contact);
        contactsDAO.create(contact);
        status.setComplete();
        return "redirect:viewAllContacts.do";
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.GET)
    public ModelAndView edit(final @RequestParam("id") Integer id) {
        final ModelAndView mav = new ModelAndView("editContact");
//        final Contact contact = contactsDAO.getById(id);
        final Contact contact = contactsDAO.getById(id);
        mav.addObject("editContact", contact);
        return mav;
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
    public String update(final @ModelAttribute("editContact") Contact contact,
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
    public ModelAndView delete(final @RequestParam("id") Integer id) {
        final ModelAndView mav = new ModelAndView("redirect:viewAllContacts.do");
//        contactsDAO.delete(id);
        final Contact contact = contactsDAO.read(id);
        contactsDAO.delete(contact);
        return mav;
    }

}
