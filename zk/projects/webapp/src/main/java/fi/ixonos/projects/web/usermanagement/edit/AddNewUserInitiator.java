package fi.ixonos.projects.web.usermanagement.edit;

import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.CodeTableService;
import fi.ixonos.projects.logic.service.UsersService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;

public class AddNewUserInitiator extends AnnotateDataBinderInit {

    @Resource
    private CodeTableService codeTableService = ( CodeTableService) SpringUtil.getApplicationContext().getBean("codeTableService");
    @Resource
    protected UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");

    @Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {
        List<Users> users = usersService.getAllUsers();
        List<CodeTable> authorities = new ArrayList<CodeTable>();
        try {
            authorities = codeTableService.getCodes("AUTHORITIES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Users> managerList = new ArrayList<Users>();
        managerList.add(null);
        for (Users user : users) {
            managerList.add(user);
        }
        page.setVariable("managerList", managerList);
        page.setVariable("authorities", authorities);
        Listbox usersListbox = (Listbox) Executions.getCurrent().getArg().get("usersListbox");
        AddNewUserWindow addNewUserWindow = (AddNewUserWindow) comps[0];
        addNewUserWindow.setUsersListbox(usersListbox);
        super.doAfterCompose(page, comps);
    }
}
