package com.ixonos.skillnet.web.usermanagement.editDetail;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.util.window.CurriculumWindow;

/**
 *
 * @author katrami
 */
public class UsersEditDetailWindow extends Window implements UserEditDetailWindowInterface {

    private static final long serialVersionUID = 1L;
    private Users user;
    private UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");

    /**
     * @{@inheritDoc}
     */
    @Override
    public void changeUserDetail(Event event) throws Exception {
        usersService.updateUsersInfo(user.getUsername(), user);
        Messagebox.show(Labels.getLabel("userDetail.information.userUpdated"), Labels.getLabel("userDetail.information"), Messagebox.OK, Messagebox.INFORMATION);
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void uploadCV(Event event) throws Exception {

        org.zkoss.util.media.Media[] media = Fileupload.get(-1);
        InputStream x;

        final ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        Media medIter = null;
        if (media != null) {
            for (int i = 0; i < media.length; i++) {
                medIter = media[i];

                byte[] buffer = new byte[8192];
                if (medIter.isBinary()) {
                    x = medIter.getStreamData();
                    while (x.read(buffer) != -1) {
                        byteArrayOutput.write(buffer);
                    }
                }
            }
        }

        if (medIter != null) {
            usersService.updateCurriculum(user.getUsername(), byteArrayOutput.toByteArray());
            Messagebox.show(Labels.getLabel("userDetail.information.cvUpdated"), Labels.getLabel("userDetail.information"), Messagebox.OK, Messagebox.INFORMATION);
            user.setIsCurriculumAlreadyFillUp(true);
            refreshUsersListbox();
        }
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void refreshUsersListbox() {
        final Listbox usersListbox = (Listbox) this.getFellow("usersListbox");
        final List userList = (List) usersListbox.getModel();
        userList.clear();
        userList.add(user);

    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void downloadCV(Event event) throws Exception {
        CurriculumWindow.downloadCV(event, user);
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public Users getUser() {
        return user;
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void setUser(Users user) {
        this.user = user;
    }
}
