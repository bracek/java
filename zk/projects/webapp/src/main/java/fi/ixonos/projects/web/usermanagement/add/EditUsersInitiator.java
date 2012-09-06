//package fi.ixonos.projects.web.usermanagement.add;
//
//import fi.ixonos.projects.logic.bean.Users;
//import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
//import fi.ixonos.projects.logic.service.UsersService;
//import java.util.List;
//
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.Page;
//import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
//import org.zkoss.zul.Listbox;
//
///**
// *
// * @author katrami
// * @date Oct 13, 2010
// */
//public final class EditUsersInitiator extends AnnotateDataBinderInit {
//
//    private UsersService usersService = (UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");
//
//    @Override
//    public void doAfterCompose(Page page, Component[] comps) throws Exception {
//        List<Users> users = usersService.readAll();
//        page.setVariable("usersList", users);
//
//        Listbox usersListbox = (Listbox) Executions.getCurrent().getArg().get("usersListbox");
//        EditUsersWindow projectWindow = (EditUsersWindow) comps[0];
//        projectWindow.setUsersListbox(usersListbox);
//        super.doAfterCompose(page, comps);
//
//    }
//}
