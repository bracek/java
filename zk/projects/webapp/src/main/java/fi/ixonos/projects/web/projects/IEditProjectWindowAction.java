package fi.ixonos.projects.web.projects;

import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author katrami
 * @date Oct 21, 2010
 */
public interface IEditProjectWindowAction {

    /**
     * add user for specific project
     * @param event
     * @throws Exception
     */
    void onAddUserToProject(Event event) throws Exception;

    /**
     * delete user from project
     * @param event
     * @throws Exception
     */
    void onDeleteUserFromProject(Event event) throws Exception;
}
