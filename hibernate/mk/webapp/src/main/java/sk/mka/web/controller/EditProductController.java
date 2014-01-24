package sk.mka.web.controller;

import com.ixonos.skillnet.logic.bean.Product;
import com.ixonos.skillnet.logic.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sk.mka.web.validator.ProductValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static sk.mka.util.StringUtil.doRedirect;

@Controller
@RequestMapping("/editProduct.do")
public class EditProductController {

    // -------------------------------- ATTRS ----------------------------------
    /**
     * The logger.
     */
    private static final Log logger = LogFactory
            .getLog(EditProductController.class);
    public static final String PRODUCT = "product";
    /**
     * The Constant viewName.
     */
    public static final String viewName = "editProduct";
    /**
     * The product manager service.
     */
    @Resource
    private ProductService productService;
    @Resource
    private ProductValidator validator;

    // ----------------------------- CONSTRUCTORS ------------------------------

    // -------------------------------- METHODS --------------------------------

    /**
     * Handle method GET.
     *
     * @param id      the id
     * @param model   the model
     * @param request the request
     * @return the string
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET)
    public String handle(@RequestParam(value = PRODUCT,
            required = false) final Integer id,
                         final ModelMap model, final HttpServletRequest request)
            throws Exception {
        logger.debug("handle()[GET]: has just been started. Given product id "
                + id);

        Product product = new Product();
        if (id != null) {
            product = productService.findById(id);
        }
        model.put(PRODUCT, product);

        return viewName;
    }

    /**
     * Handle method POST.
     *
     * @param product the product
     * @param request the request
     * @return the string
     * @throws Exception the exception
     */
    @RequestMapping(method = RequestMethod.POST)
    // Note: BindingResult parameter must follow the ModelAttribute parameter
    public String handle(final @ModelAttribute(PRODUCT) Product product,
                         final BindingResult result, final HttpServletRequest request)
            throws Exception {
        logger.debug("handle()[POST]: has just been started. Given product is "
                + product);

        validator.validate(product, result);

        if (result.hasErrors()) {
            return viewName;
        }

        productService.saveProduct(product);
        return doRedirect("register");
    }
}
