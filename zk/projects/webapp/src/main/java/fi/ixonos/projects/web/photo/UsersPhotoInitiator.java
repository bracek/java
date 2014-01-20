package fi.ixonos.projects.web.photo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Image;
import org.zkoss.zul.Window;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.utils.PhotoUtil;

/**
 * 
 * @author kendrto
 */
public class UsersPhotoInitiator extends AnnotateDataBinderInit {

    protected UsersService usersService = (final UsersService) SpringUtil.getApplicationContext().getBean("usersService");

    @Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {

        Window usersPhotolInitiator = (Window) comps[0];

        Users user = (Users) Executions.getCurrent().getArg().get("user");
        PhotoUtil.refreshPhoto(user, (Image) usersPhotolInitiator.getFellow("imagePhoto"), null, true);

        super.doAfterCompose(page, comps);
    }
}
