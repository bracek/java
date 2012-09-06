package fi.ixonos.projects.web.usermanagement.editDetail;

import fi.ixonos.projects.logic.bean.Users;
import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author katrami
 */
public interface IUserEditDetailWindow {

    String CURRICULUM_DOC_SUFFIX = "_cv.doc";

    void changeUserDetail(Event event) throws Exception;

    /**
     * handle getting curriculum for logged user
     * @param event
     * @throws java.lang.Exception
     */
    void downloadCV(Event event) throws Exception;

    /**
     *
     * @return selected user
     */
    Users getUser();

    /**
    change  listbox model for current user 
     */
    void refreshUsersListbox();

    void setUser(Users user);

    /**
     * handle putting cv to the server for logged user
     * @param event
     * @throws java.lang.Exception
     */
    void uploadCV(Event event) throws Exception;

    /**
     * upload photo
     * @param event
     * @throws Exception
     */
    void uploadPhoto(Event event) throws Exception;

    void deletePhoto(Event event) throws Exception;
}
