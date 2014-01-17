package fi.ixonos.projects.web.usermanagement.edit;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import fi.ixonos.projects.logic.bean.Authority;
import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.bean.Users;

public class RolesListitemRenderer implements ListitemRenderer {

    @Override
    public void render(final Listitem item,final  Object data) throws Exception {
        CodeTable renderedAuthority = (CodeTable) data;
        Users user = (Users) ((Listitem) getParentComponent(item, Listitem.class)).getValue();
        List<Authority> authorityCollection = user.getAuthorityCollection();
        boolean select = false;
        for (Authority authority : authorityCollection) {
            CodeTable usersAuthority = authority.getAuthority();
            if (renderedAuthority.getCode().equals(usersAuthority.getCode())) {
                select = true;
                break;
            }
        }
        Bandbox bandbox = (Bandbox) getParentComponent(item, Bandbox.class);
        if (select) {
            if (bandbox.getText().length() > 0) {
                bandbox.setText(bandbox.getText() + ", ");
            }
            bandbox.setText(bandbox.getText() + renderedAuthority.getCode());
        }
        user.setAuthorities(bandbox.getText());
        item.setValue(data);
        item.setSelected(select);
        item.setLabel(renderedAuthority.getCode());
    }

    private Component getParentComponent(final Component component,final  Class clazz) throws Exception {
        int index = 0;
        while ((component != null) && !clazz.isInstance((component = component.getParent()))) {
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
}
