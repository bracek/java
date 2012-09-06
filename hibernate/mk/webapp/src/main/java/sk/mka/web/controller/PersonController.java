package sk.mka.web.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ixonos.skillnet.logic.bean.Person;
import com.ixonos.skillnet.logic.dao.PersonDao;

/**
 * Person form controller.
 */
@Controller
public class PersonController {

    final Logger logger = LoggerFactory.getLogger(PersonController.class);
    static public final String SEARCH_VIEW_KEY = "/person/search";
    static public final String SEARCH_MODEL_KEY = "persons";
    @Autowired
    protected PersonDao personDao = null;

    /**
     * Deletes a person.
     */
    @RequestMapping(value = "/person/delete.do")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        Person person = new Person();
        person.setId(id);

        personDao.delete(person);

        return new ModelAndView(SEARCH_VIEW_KEY, SEARCH_MODEL_KEY, search());
    }

    /**
     * Searches for all persons and returns them in a
     * <code>Collection</code> as 'persons' in the
     * <code>ModelMap</code>.
     */
    @RequestMapping(value = "/person/search.do")
    @ModelAttribute(SEARCH_MODEL_KEY)
    public Collection<Person> search() {
        Collection<Person> lResults = personDao.findPersons();

        return lResults;
    }
}
