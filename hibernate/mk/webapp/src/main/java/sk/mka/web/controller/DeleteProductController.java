package sk.mka.web.controller;

import static sk.mka.util.StringUtil.doRedirect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ixonos.skillnet.logic.service.ProductService;

@Controller
@RequestMapping("/deleteProduct.do")
public class DeleteProductController {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory
			.getLog(DeleteProductController.class);
	/** The product manager service. */
	public static final String PRODUCT = "product";

	@Resource
	private ProductService productService;

	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Handle request, method <code>GET</code>.
	 * 
	 * @param id
	 *            the id
	 * @param request
	 *            the request
	 * 
	 * @return the string
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String handle(			final @RequestParam(value = PRODUCT,
 required = false) Integer id,
			final HttpServletRequest request) throws Exception {
		logger.debug("handle()[GET]: has just been started. Given product id "
				+ id);

		if (id != null && id > 0) {
			productService.deleteProduct(id);
		}

		return doRedirect("register");
	}
}
