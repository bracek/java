package com.ixonos.skillnet.web.usermanagement.edit;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.UsersService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class AddNewUserInitiator extends AnnotateDataBinderInit {
    @Resource
    private final CodeTableService codeTableService = (CodeTableService) SpringUtil
            .getApplicationContext().getBean("codeTableService");

    @Resource
    protected UsersService usersService = (UsersService) SpringUtil
            .getApplicationContext().getBean("usersService");

    @Override
    public void doAfterCompose(final Page page,
                               final Component[] comps)
            throws Exception {
        final List<Users> users = usersService.getAllUsers();
        List<CodeTable> authorities = new ArrayList<CodeTable>();
        try {
            authorities = codeTableService.getCodes("AUTHORITIES");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        final List<Users> managerList = new ArrayList<Users>();
        managerList.add(null);
        for (final Users user : users) {
            managerList.add(user);
        }
        page.setVariable("managerList", managerList);
        page.setVariable("authorities", authorities);
        final Listbox usersListbox = (Listbox) Executions.getCurrent().getArg()
                .get("usersListbox");
        final AddNewUserWindow addNewUserWindow = (AddNewUserWindow) comps[0];
        addNewUserWindow.setUsersListbox(usersListbox);
        super.doAfterCompose(page, comps);
    }
}
