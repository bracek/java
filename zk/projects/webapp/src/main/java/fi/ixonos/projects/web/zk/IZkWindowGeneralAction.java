package fi.ixonos.projects.web.zk;

import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author katrami
 * @date Oct 20, 2010
 */
public interface IZkWindowGeneralAction<T> {

    /***
     * Method is triggered, when add button is pressed
     * @param event
     * @throws Exception
     */
     void onAdd(final Event event) throws Exception;

    /***
     * Method is triggered when update button is pressed
     * @param event
     * @throws Exception
     */
     void onUpdate(final Event event) throws Exception;

    /**
     * Method is triggered when delete button is pressed
     * @param event
     * @throws Exception
     */
     void onDelete(final Event event) throws Exception;

    /**
     * Method checks if new value for binded object is alright 
     * @param t
     * @return
     */
    boolean checkValues(T t) throws Exception;

    /**
     * refresh list of projects
     */
    void refreshView();
}
