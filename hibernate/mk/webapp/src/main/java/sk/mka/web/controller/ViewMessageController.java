package sk.mka.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ixonos.skillnet.logic.service.MessageService;

/**
 *
 * @author katrami
 */
@Controller
@RequestMapping(ApplicationCodes.MESSAGE.VIEW_REQUEST_MAPPING)
public class ViewMessageController {

    @Resource
    private MessageService messageService;

    @SuppressWarnings("unchecked")
    @RequestMapping
    public String handle(final ModelMap model,
final  HttpServletRequest request,
final  HttpServletResponse response) throws Exception {
        model.put("messages", messageService.getList());
        return ApplicationCodes.MESSAGE.VIEW_MESSAGE_KEY;
    }
}
