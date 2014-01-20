package builder;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;

/**
 *
 * @author katrami
 * @date Oct 13, 2010
 */
public final class BuilderInitiator extends AnnotateDataBinderInit {

    //TODO napln list 
    @Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {
//        List<Users> users = usersService.getAllUsers();
//        List<CodeTable> authorities = new ArrayList<CodeTable>();
//        try {
//            authorities = codeTableService.getCodes("AUTHORITIES");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<Users> managerList = new ArrayList<Users>();
//        managerList.add(null);
//        for (Users user : users) {
//            managerList.add(user);
//        }
//        page.setVariable("managerList", managerList);
//        page.setVariable("authorities", authorities);
//        Listbox usersListbox = (Listbox) Executions.getCurrent().getArg().get("usersListbox");
//        AddNewUserWindow addNewUserWindow = (AddNewUserWindow) comps[0];
//        addNewUserWindow.setUsersListbox(usersListbox);
        super.doAfterCompose(page, comps);
    }
}
