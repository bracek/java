package com.ixonos.skillnet.web.usermanagement.edit;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;

public class ChangePasswordInitiator extends AnnotateDataBinderInit {

	@Override
	public void doAfterCompose(final Page page, final Component[] comps)
			throws Exception {
		final String userName = (String) Executions.getCurrent().getArg()
				.get("userName");
		final Boolean showOldPassword = (Boolean) Executions.getCurrent()
				.getArg().get("showOldPassword");
		page.setVariable("userName", userName);
		page.setVariable("showOldPassword", showOldPassword);
		page.setVariable("notShowOldPassword", !showOldPassword);
		final ChangePasswordWindow changePasswordWindow = (ChangePasswordWindow) comps[0];
		changePasswordWindow.setUserName(userName);
		super.doAfterCompose(page, comps);
	}
}
