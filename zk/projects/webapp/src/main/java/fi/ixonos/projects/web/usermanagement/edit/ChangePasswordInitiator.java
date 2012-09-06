package fi.ixonos.projects.web.usermanagement.edit;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;

public class ChangePasswordInitiator extends AnnotateDataBinderInit {
	
	@Override
	public void doAfterCompose(Page page, Component[] comps) throws Exception {		
		String userName = (String)Executions.getCurrent().getArg().get("userName");		
		Boolean showOldPassword = (Boolean)Executions.getCurrent().getArg().get("showOldPassword");
		page.setVariable("userName", userName);
		page.setVariable("showOldPassword", showOldPassword);
		page.setVariable("notShowOldPassword", !showOldPassword);
		ChangePasswordWindow changePasswordWindow = (ChangePasswordWindow)comps[0];
		changePasswordWindow.setUserName(userName);
		super.doAfterCompose(page, comps);
	}
}
