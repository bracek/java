package stibrik.springapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ondrej Stibrik
 *
 */
@Controller
@RequestMapping("/hello.do")
public class HelloController {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory.getLog(HelloController.class);
	
	/** The Constant viewName. */
	public static final String viewName = "hello";

	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Handle.
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
		String now = (new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")).format(new Date());
		logger.debug("handle(): Returning hello view with " + now);
		
		model.put("now", now);
		
		return viewName;
	}	
}
