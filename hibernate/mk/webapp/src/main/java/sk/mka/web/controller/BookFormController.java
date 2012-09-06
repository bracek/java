package sk.mka.web.controller;

import java.util.Collection;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ixonos.skillnet.logic.bean.Book;
import com.ixonos.skillnet.logic.service.BookService;

/**
 *
 * @author katrami
 */
@Controller
public class BookFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    public static final String BOOK = "book";
    /** The Constant viewName. */
    protected String VIEW_KEY = "book/viewBook";
    private static final String FORM_VIEW_KEY = "/book/save";
    private static final String SEARCH_VIEW_KEY = "/book/viewBooks";
    private static final String FORM_MODEL_KEY = "book";
    private static final String SEARCH_MODEL_KEY = "books";
    @Resource
    private BookService bookService;

    /**
     * Creates a person object and forwards to person form.
     */
    @RequestMapping(value = "/book/create.do")
    public Book create() {
        return new Book();
    }

    /**
     * Gets a person based on it's id.
     */
    @RequestMapping(value = "/book/edit.do")
    public ModelAndView info(@RequestParam("id") Integer id) {
        Book result = bookService.load(id);
        return new ModelAndView(FORM_VIEW_KEY, FORM_MODEL_KEY, result);
    }

    /**
     * Saves a person.
     */
    @RequestMapping(value = "/book/save.do")
    public ModelAndView save(Book book) {
        bookService.create(book);
        return new ModelAndView(SEARCH_VIEW_KEY, search());
    }

    /**
     * Searches for all persons and returns them in a
     * <code>Collection</code> as 'persons' in the
     * <code>ModelMap</code>.
     */
    @RequestMapping(value = "/book/viewBooks.do")
    public ModelMap search() {
        Collection<Book> lResults = bookService.loadAll();
        return new ModelMap(SEARCH_MODEL_KEY, lResults);
    }

    /**
     * Deletes a person.
     */
    @RequestMapping(value = "/book/delete.do")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        Book book = bookService.load(id);
        bookService.delete(book);
        return new ModelAndView(SEARCH_VIEW_KEY, search());
    }
}
