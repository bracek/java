package com.ixonos.skillnet.web.usermanagement.edit;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AddNewUserWindow extends Window {
    private Users user;
    private Listbox usersListbox;

    public AddNewUserWindow() {
        user = new Users();
        user.setEnabled(Boolean.TRUE);
    }

    public Users getUser() {
        return user;
    }

    public void setUser(final Users user) {
        this.user = user;
    }

    public void onAddNewUser() throws Exception {
        final String login = user.getUsername();
        if (login == null || login.trim().length() == 0) {
            Messagebox.show(Labels.getLabel("userAddNew.error.loginNotSpec"),
                    Labels.getLabel("userAddNew.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return;
        }
        final Textbox passwordBox = (Textbox) this.getFellow("password");
        final Textbox confirmationBox = (Textbox) this
                .getFellow("confirmation");
        if (passwordBox.getValue().trim().length() == 0
                || confirmationBox.getValue().trim().length() == 0) {
            Messagebox.show(Labels.getLabel("userAddNew.error.passwNotSpec"),
                    Labels.getLabel("userAddNew.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return;
        }
        if (!passwordBox.getValue().equals(confirmationBox.getValue())) {
            Messagebox.show(Labels.getLabel("userAddNew.error.passNotSame"),
                    Labels.getLabel("userAddNew.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return;
        }
        final String password = user.getPassword();
        final Boolean enabled = user.getEnabled();
        final Users manager = user.getManager();
        final Listbox lb = (Listbox) this.getFellow("usersAuthorities");
        final Set selectedItems = lb.getSelectedItems();
        final Iterator selectedIterator = selectedItems.iterator();
        final List<String> authorities = new ArrayList<String>();
        while (selectedIterator.hasNext()) {
            final Listitem li = (Listitem) selectedIterator.next();
            final CodeTable selectedAuthority = (CodeTable) li.getValue();
            authorities.add(selectedAuthority.getCode());
        }
        final UsersService usersService = (UsersService) SpringUtil
                .getApplicationContext().getBean("usersService");
        try {
            if (!usersService.isUserAlreadyRegistered(login)) {
                // TODO set right user properties
                usersService.addNewUser(login, password, "<name>", "<surname>",
                        "<email>", "<phone>", null, null, enabled, manager,
                        authorities);
            } else {
                Messagebox.show(Labels.getLabel("userAddNew.error.userExist"),
                        Labels.getLabel("userAddNew.error"), Messagebox.OK,
                        Messagebox.ERROR);
                return;
            }
        } catch (final Exception e) {
            Messagebox.show(Labels.getLabel(e.getMessage()),
                    Labels.getLabel("userAddNew.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return;
        }
        this.detach();
        // refresh listbox with users
        final List<Users> users = usersService.getAllUsers();
        this.getUsersListbox().setModel(new BindingListModelList(users, true));
    }

    public void onAuthoritySelect(final SelectEvent event) throws Exception {
        final Set selectedItems = event.getSelectedItems();
        final Iterator selectedIterator = selectedItems.iterator();
        final List<String> selectedAuthorities = new ArrayList<String>();
        while (selectedIterator.hasNext()) {
            final Listitem li = (Listitem) selectedIterator.next();
            final CodeTable selectedAuthority = (CodeTable) li.getValue();
            selectedAuthorities.add(selectedAuthority.getCode());
        }
        final Listbox lb = (Listbox) event.getTarget();

        final StringBuilder sb = new StringBuilder();
        for (final String selectedAuthority : selectedAuthorities) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append(selectedAuthority);
        }
        final Bandbox bandbox = (Bandbox) getParentComponent(lb, Bandbox.class);
        bandbox.setText(sb.toString());

    }

    private Component getParentComponent(Component component,
                                         final Class clazz)
            throws Exception {
        int index = 0;
        while (component != null
                && !clazz.isInstance(component = component.getParent())) {
            index++;
            if (index == 50) {
                throw new Exception("No parent " + clazz + " found!");
            }
        }
        if (component == null) {
            throw new Exception("No parent " + clazz + " found!");
        }
        return component;
    }

    public void setUsersListbox(final Listbox usersListbox) {
        this.usersListbox = usersListbox;
    }

    public Listbox getUsersListbox() {
        return usersListbox;
    }
}
