package fi.ixonos.projects.web.usermanagement.editDetail;

import java.util.Arrays;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Image;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.utils.PhotoUtil;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;

/**
 * 
 * @author katrami
 */
public class UsersEditDetailInitiator extends AnnotateDataBinderInit {

	protected UsersService usersService = (final UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");

	@Override
	public void doAfterCompose(final Page page,final  Component[] comps) throws Exception {

		final String userName = ((User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getUsername();
		UsersEditDetailWindow usersEditDetailWindow = (UsersEditDetailWindow) comps[0];

		Users user = new Users();
		user.setUsername(userName);
		List<Users> users = usersService.readByCriteria(user);
		user = users.get(0);
		if (!Arrays.equals(user.getCurriculum(), null)) {
			user.setIsCurriculumAlreadyFillUp(true);
		} else {
			user.setIsCurriculumAlreadyFillUp(false);
		}
		if (!Arrays.equals(user.getPhoto(), null)) {
			user.setPhotoUploaded(true);
		} else {
			user.setPhotoUploaded(false);
		}
		usersEditDetailWindow.setUser(user);

		page.setVariable("userList", users);
		
		PhotoUtil.refreshPhoto(user, (Image) usersEditDetailWindow.getFellow("imagePhoto"), usersEditDetailWindow.getFellow("deletePhoto"), false);
		
		super.doAfterCompose(page, comps);
	}
}
