package sk.mka.web.controller;

import com.ixonos.skillnet.logic.service.ProductService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register.do")
public class RegisterController {

    // -------------------------------- ATTRS ----------------------------------
    /** The logger. */
    private static final Log logger = LogFactory.getLog(RegisterController.class);
    /** The Constant viewName. */
    public static final String viewName = "register";
    /** The product manager service. */
    @Resource
    private ProductService productService;

    // ----------------------------- CONSTRUCTORS ------------------------------

    // -------------------------------- METHODS --------------------------------
    /**
     * Handle request.
     *
     * @param model the model
     * @param request the request
     * @param response the response
     *
     * @return the string
     *
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping
    public String handle(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("handle(): has just been started.");

        model.put("products", productService.getProducts());

        return viewName;
    }
}
