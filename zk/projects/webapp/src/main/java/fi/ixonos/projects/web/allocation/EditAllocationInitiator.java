/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ixonos.projects.web.allocation;

import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.UsersService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author polakja
 */
public class EditAllocationInitiator extends AnnotateDataBinderInit {

    private UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");

    @Override
    public void doAfterCompose(final Page page,final  Component[] comps) throws Exception {
        getWindow(comps).init();
	page.setVariable("userSourceList", new ListModelList(usersService.getSortedUsers("surname")));
        super.doAfterCompose(page, comps);
    }

    private EditAllocationWindow getWindow(final Component[] comps) {
        for (Component comp : comps)
            if (comp instanceof EditAllocationWindow)
                return (EditAllocationWindow) comp;
        throw new IllegalArgumentException("Missing required argument : " + EditAllocationWindow.class.getName());
    }
}
