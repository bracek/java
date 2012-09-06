package com.ixonos.skillnet.web.companytree;

import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author katrami
 */
public interface CompanyTreeModelControlerInterface {

   /**
    * if user has ROLE_GROUP_MANAGER, can download curriculum vitae for users
    */
    void checkPermissionForDownloadCV();

    /**
     * handle getting curriculum for logged user
     * @param event
     * @throws java.lang.Exception
     */
    void onClick$downloadCV(Event event) throws Exception;
}
