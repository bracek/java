package com.ixonos.skillnet.web.usermanagement.editDetail;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.util.window.CurriculumWindow;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author katrami
 */
public class UsersEditDetailWindow extends Window implements
        UserEditDetailWindowInterface {

    private static final long serialVersionUID = 1L;
    private Users user;
    private final UsersService usersService = (UsersService) SpringUtil
            .getApplicationContext().getBean("usersService");

    /**
     * @{@inheritDoc
     */
    @Override
    public void changeUserDetail(final Event event) throws Exception {
        usersService.updateUsersInfo(user.getUsername(), user);
        Messagebox.show(Labels.getLabel("userDetail.information.userUpdated"),
                Labels.getLabel("userDetail.information"), Messagebox.OK,
                Messagebox.INFORMATION);
    }

    /**
     * @{@inheritDoc
     */
    @Override
    public void uploadCV(final Event event) throws Exception {

        final org.zkoss.util.media.Media[] media = Fileupload.get(-1);
        InputStream x;

        final ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        Media medIter = null;
        if (media != null) {
            for (int i = 0; i < media.length; i++) {
                medIter = media[i];

                final byte[] buffer = new byte[8192];
                if (medIter.isBinary()) {
                    x = medIter.getStreamData();
                    while (x.read(buffer) != -1) {
                        byteArrayOutput.write(buffer);
                    }
                }
            }
        }

        if (medIter != null) {
            usersService.updateCurriculum(user.getUsername(),
                    byteArrayOutput.toByteArray());
            Messagebox.show(
                    Labels.getLabel("userDetail.information.cvUpdated"),
                    Labels.getLabel("userDetail.information"), Messagebox.OK,
                    Messagebox.INFORMATION);
            user.setIsCurriculumAlreadyFillUp(true);
            refreshUsersListbox();
        }
    }

    /**
     * @{@inheritDoc
     */
    @Override
    public void refreshUsersListbox() {
        final Listbox usersListbox = (Listbox) this.getFellow("usersListbox");
        final List userList = (List) usersListbox.getModel();
        userList.clear();
        userList.add(user);

    }

    /**
     * @{@inheritDoc
     */
    @Override
    public void downloadCV(final Event event) throws Exception {
        CurriculumWindow.downloadCV(event, user);
    }

    /**
     * @{@inheritDoc
     */
    @Override
    public Users getUser() {
        return user;
    }

    /**
     * @{@inheritDoc
     */
    @Override
    public void setUser(final Users user) {
        this.user = user;
    }
}
