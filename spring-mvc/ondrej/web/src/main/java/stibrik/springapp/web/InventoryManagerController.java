package stibrik.springapp.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import stibrik.springapp.service.ProductService;

/**
 * The Class InventoryManagerController.
 * 
 * @author Ondrej Stibrik
 */
@Controller
@RequestMapping("/inventory.do")
public class InventoryManagerController {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory.getLog(InventoryManagerController.class);
	
	/** The Constant viewName. */
	public static final String viewName = "inventory";
	
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
	public String handle(final ModelMap model,final  HttpServletRequest request,final  HttpServletResponse response) throws Exception {
		logger.debug("handle(): has just been started.");
		
		model.put("products", productService.getProducts());
		
		return viewName;
	}
}
