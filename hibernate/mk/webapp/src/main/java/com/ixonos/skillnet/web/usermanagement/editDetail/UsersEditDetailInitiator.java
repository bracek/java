package com.ixonos.skillnet.web.usermanagement.editDetail;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.security.SkillnetUser;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * 
 * @author katrami
 */
public class UsersEditDetailInitiator extends AnnotateDataBinderInit {

	protected UsersService usersService = (final UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");

	@Override
	public void doAfterCompose(final Page page,
 final Component[] comps)
			throws Exception {

		final String userName = ((SkillnetUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getUsername();
		final UsersEditDetailWindow usersEditDetailWindow = (UsersEditDetailWindow) comps[0];

		Users user = new Users();
		user.setUsername(userName);
		final List<Users> users = usersService.readByCriteria(user);
		user = users.get(0);
		usersEditDetailWindow.setUser(user);
		if (!Arrays.equals(user.getCurriculum(), null)) {
			user.setIsCurriculumAlreadyFillUp(true);
		} else {
			user.setIsCurriculumAlreadyFillUp(false);
		}

		page.setVariable("userList", users);
		super.doAfterCompose(page, comps);
	}
}
