package com.ixonos.skillnet.web.usermanagement.edit;

import com.ixonos.skillnet.logic.bean.Users;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

public class UserComboitemRenderer implements ComboitemRenderer {

    public void render(final Comboitem item,final  Object data) throws Exception {
        if (data instanceof Users) {
            Users user = (Users) data;
            if (user != null) {
//                item.setLabel(user.getUsername());
                item.setLabel(user.getHumanReadableUsername());
            } else {
                item.setContent("&nbsp;");
            }
        }
    }
}
