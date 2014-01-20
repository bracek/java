package fi.ixonos.projects.web.projects;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.ProjectsService;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zul.Listbox;

/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public class EditProjectInitiator extends AnnotateDataBinderInit {

    private ProjectsService projectsService = (final ProjectsService) ProjectsApplicationContext.getApplicationContext().getBean("projectsService");
    private UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");

    @Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {
        List<Projects> projects = projectsService.readAll();
        page.setVariable("projectsList", projects);

        Listbox projectsListbox = (Listbox) Executions.getCurrent().getArg().get("projectsListbox");
        EditProjectWindow projectWindow = (EditProjectWindow) comps[0];
        projectWindow.setProjectsListbox(projectsListbox);

        final List<Users> users = usersService.getSortedUsers("surname");
        page.setVariable("allUsersList", users);

        super.doAfterCompose(page, comps);

    }
}
