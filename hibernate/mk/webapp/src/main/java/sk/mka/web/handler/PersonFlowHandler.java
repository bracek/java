package sk.mka.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;
import sk.mka.web.controller.PersonController;

/**
 * Person flow handler.
 * 
 * @author David Winterfeldt
 */
@Component
public class PersonFlowHandler extends AbstractFlowHandler {

    /**
     * Where the flow should go when it ends.
     */
    @Override
    public String handleExecutionOutcome(final FlowExecutionOutcome outcome,final             HttpServletRequest request,final  HttpServletResponse response) {
        return getContextRelativeUrl(PersonController.SEARCH_VIEW_KEY);
    }

    /**
     * Where to redirect if there is an exception not handled by the flow.
     */
    @Override
    public String handleException(final FlowException e,final             HttpServletRequest request,final  HttpServletResponse response) {
        if (e instanceof NoSuchFlowExecutionException) {
            return getContextRelativeUrl(PersonController.SEARCH_VIEW_KEY);
        } else {
            throw e;
        }
    }

    /**
     * Gets context relative url with an '.html' extension.
     */
    private String getContextRelativeUrl(final String view) {
        return "contextRelative:" + view + ".do";
    }
}
