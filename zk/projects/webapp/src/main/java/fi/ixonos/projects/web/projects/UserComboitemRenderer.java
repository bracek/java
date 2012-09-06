package fi.ixonos.projects.web.projects;

import fi.ixonos.projects.logic.bean.Users;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

/**
 *
 * @author katrami
 * @date Oct 19, 2010
 */
public class UserComboitemRenderer implements ComboitemRenderer {

    @Override
    public void render(Comboitem item, Object data) throws Exception {
        if (data instanceof Users) {
            Users user = (Users) data;
            if (user != null) {
                item.setLabel(user.getUsername() + " - [ " + user.getName() + " " + user.getSurname() + " ]");
            } else {
                item.setContent("&nbsp;");
            }
        }
    }
}
