package sk.mka.web.controller;

import com.ixonos.skillnet.logic.bean.Osoba;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.OsobaDao;
import java.util.Collection;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sk.mka.web.errors.PersonErrors;
import sk.mka.web.validator.OsobaValidator;

@Controller
public class OsobaController {

    final Logger logger = LoggerFactory.getLogger(OsobaController.class);
    private static final String FORM_VIEW_KEY = "/save/osoba";
    private static final String SEARCH_VIEW_KEY = "/person/viewOsoba";
    private static final String FORM_MODEL_KEY = "osoba";
    private static final String SEARCH_MODEL_KEY = "osobas";
    @Autowired
    protected OsobaDao osobaDAO = null;
    public static final String OSOBA = "osoba";
    @Resource
    private OsobaValidator validator;

    /**
     * Creates a person object and forwards to person form.
     */
    @RequestMapping(value = "/person/saveOsoba.do")
    public Osoba create() {
        return new Osoba();
    }

    /**
     * Saves a person.
     */
    @RequestMapping(value = "/save/osoba.do")
    public ModelAndView save(Osoba osoba) {
        PersonErrors errors = new PersonErrors();
        validator.validate(osoba, errors);

        if (osobaDAO instanceof GenericDAO) {
            GenericDAO genericDAO = (GenericDAO) osobaDAO;
            genericDAO.create(osoba);
        }

        return new ModelAndView(FORM_VIEW_KEY, FORM_MODEL_KEY, osoba);
    }

    /**
     * Gets a person based on it's id.
     */
    @RequestMapping(value = "/info/osoba.do")
    public ModelAndView info(@RequestParam("id") Integer id) {

        Object result = null;
//        if (osobaDAO instanceof GenericDAO) {
//            GenericDAO genericDAO = (GenericDAO) osobaDAO;
//            result = (Osoba) genericDAO.load(id);
//        }
        return new ModelAndView(FORM_VIEW_KEY, FORM_MODEL_KEY, result);
    }

    /**
     * Deletes a person.
     */
    @RequestMapping(value = "/delete/osoba.do")
    public ModelAndView delete(@RequestParam("id") Integer id) {


//        if (osobaDAO instanceof GenericDAO) {
//            GenericDAO genericDAO = (GenericDAO) osobaDAO;
//            Osoba osoba = (Osoba) genericDAO.load(id);
//            genericDAO.delete(osoba);
//        }

        return new ModelAndView(SEARCH_VIEW_KEY, search());
    }

    /**
     * Searches for all persons and returns them in a
     * <code>Collection</code> as 'persons' in the
     * <code>ModelMap</code>.
     */
    @RequestMapping(value = "/osoba/viewOsobas.do")
    public ModelMap search() {

        Collection<Osoba> lResults = null;

//        if (osobaDAO instanceof GenericDAO) {
//            GenericDAO genericDAO = (GenericDAO) osobaDAO;
//            lResults = genericDAO.loadAll();
//        }

        return new ModelMap(SEARCH_MODEL_KEY, lResults);
    }
}
