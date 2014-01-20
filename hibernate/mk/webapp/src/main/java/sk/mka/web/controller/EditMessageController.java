package sk.mka.web.controller;

import static sk.mka.util.StringUtil.doRedirect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ixonos.skillnet.logic.bean.Message;
import com.ixonos.skillnet.logic.service.MessageService;

/**
 * 
 * @author katrami
 */
@Controller
@RequestMapping(ApplicationCodes.MESSAGE.EDIT_REQUEST_MAPPING)
public class EditMessageController {
	// -------------------------------- ATTRS ----------------------------------

	/** The logger. */
	private static final Log logger = LogFactory
			.getLog(EditMessageController.class);
	/** The Constant viewName. */
	public static final String viewName = "menu/message/editMessage";
	/** The product manager service. */
	@Resource
	private MessageService messageService;

	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Handle method GET.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * 
	 * @return the string
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String handle(			final @RequestParam(value = ApplicationCodes.MESSAGE.COMMAND,
 required = false) Integer id,
			final ModelMap model, final HttpServletRequest request)
			throws Exception {
		logger.debug("handle()[GET]: has just been started. Given message id "
				+ id);

		Message message = new Message();
		if (id != null) {
			message = messageService.load(id);
		}
		model.put(ApplicationCodes.MESSAGE.COMMAND, message);
		return ApplicationCodes.MESSAGE.EDIT_KEY;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handle(			final @ModelAttribute(ApplicationCodes.MESSAGE.COMMAND) Message message,
			final BindingResult result, final HttpServletRequest request)
			throws Exception {
		logger.debug("handle()[POST]: has just been started. Given product is "
				+ message);

		messageService.save(message);
		return doRedirect(ApplicationCodes.MESSAGE.VIEW_MESSAGE_KEY);
	}
}
