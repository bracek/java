package fi.ixonos.projects.web.usermanagement.editDetail;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.utils.CurriculumWindow;
import fi.ixonos.projects.web.utils.PhotoUtil;

/**
 *
 * @author katrami
 */
public class UsersEditDetailWindow extends Window implements IUserEditDetailWindow {

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
    public void uploadPhoto(Event event) throws Exception {

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
       		if (PhotoUtil.uploadPhoto(user, byteArrayOutput.toByteArray()))
       			PhotoUtil.refreshPhoto(user, (Image) this.getFellow("imagePhoto"), this.getFellow("deletePhoto"), false);
        }
    }
    
    /**
     * @{@inheritDoc}
     */
    @Override
    public void deletePhoto(Event event) throws Exception {
    		if (PhotoUtil.deletePhoto(user))
    			PhotoUtil.refreshPhoto(user, (Image) this.getFellow("imagePhoto"), this.getFellow("deletePhoto"), false);
    }
    
    public void onOpenPhoto(MouseEvent event) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/photo/userPhoto.zul", null, map);
		win.doPopup();
		win.setPosition("parent");
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
