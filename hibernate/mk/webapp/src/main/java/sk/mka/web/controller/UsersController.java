package sk.mka.web.controller;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bracek
 */
@Controller
public class UsersController {

    protected final Log logger = LogFactory.getLog(getClass());
    public static final String USERS = "users";
    /** The Constant viewName. */
    private static final String FORM_VIEW_KEY = "/users/save";
    private static final String SEARCH_VIEW_KEY = "/users/list";
    /** The Constant model key*/
    private static final String FORM_MODEL_KEY = "users";
    private static final String SEARCH_MODEL_KEY = "userss";
    @Resource
    private UsersService usersService;

    /**
     * Creates a person object and forwards to person form.
     */
    @RequestMapping(value = "/users/create.do")
    public Users create() {
        return new Users();
    }

    /**
     * Gets a person based on it's id.
     */
    @RequestMapping(value = "/users/edit.do")
    public ModelAndView edit(@RequestParam("username") String username) {
        Users result = null;
        try {
            result = usersService.getUser(username);
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView(FORM_VIEW_KEY, FORM_MODEL_KEY, result);
    }

    /**
     * Saves a person.
     */
    @RequestMapping(value = "/users/save.do")
    public ModelAndView save(Users users) {
        usersService.create(users);
        return new ModelAndView(SEARCH_VIEW_KEY, search());
    }

    /**
     * Searches for all users and returns them in a
     * <code>Collection</code> as 'users' in the
     * <code>ModelMap</code>.
     */
    @ModelAttribute(SEARCH_MODEL_KEY)
    @RequestMapping(value = "/users/list.do")
    public ModelMap search() {
//        Collection<Users> lResults = usersService.loadAll();
        Collection<Users> lResults = null;
        return new ModelMap(SEARCH_MODEL_KEY, lResults);
    }

    /**
     * Deletes a person.
     */
    @RequestMapping(value = "/users/delete.do")
    public ModelAndView delete(@RequestParam("username") String username) {
        Users readUsers = new Users();
        readUsers.setUsername(username);
//        readUsers.setEnabled(1);
        List<Users> usersList = usersService.readByCriteria(readUsers);
        Users oneUser = usersList.get(0); //can be because of PK on username for bean users
        usersService.delete(oneUser);
        return new ModelAndView(SEARCH_VIEW_KEY, search());
    }
}
