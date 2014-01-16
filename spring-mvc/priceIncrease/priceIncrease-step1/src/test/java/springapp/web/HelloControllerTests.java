package springapp.web;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.Assert.assertEquals;

public class HelloControllerTests {

    @Test
    public void testHandleRequestView() throws Exception {
        HelloController controller = new HelloController();
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("WEB-INF/jsp/hello.jsp", modelAndView.getViewName());
    }
}
