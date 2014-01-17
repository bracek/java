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

import com.ixonos.skillnet.logic.service.MessageService;

@Controller
@RequestMapping(ApplicationCodes.MESSAGE.DELETE_REQUEST_MAPPING)
public class DeleteMessageController {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory
			.getLog(DeleteMessageController.class);
	/** The product manager service. */
	@Resource
	private MessageService messageService;

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
	public String handle(
			final @RequestParam(value = ApplicationCodes.MESSAGE.COMMAND, required = false) Integer id,
			final HttpServletRequest request) throws Exception {
		logger.debug("handle()[GET]: has just been started. Given message id "
				+ id);

		if (id != null && id > 0) {
			messageService.delete(id);
		}
		return doRedirect(ApplicationCodes.MESSAGE.VIEW_MESSAGE_KEY);
	}
}
