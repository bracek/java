package fi.ixonos.projects.web.usermanagement.edit;

import fi.ixonos.projects.logic.service.UsersService;


import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class ChangePasswordWindow extends Window {
	
	 /**
     * Log4j logger for this class
     */
    private static Logger logger = Logger.getLogger(ChangePasswordWindow.class);
    private static final long serialVersionUID = 1L;
    private final UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
    public String userName;	
	
    public String getUserName() {
		return userName;
	}
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	
	public void onAdminChangePassword() throws Exception {
		if (isPasswordAndConfirmationCorrect()) {
			try {
				boolean changed=usersService.changePassword(userName, ((Textbox)this.getFellow("password")).getText());
				if (changed){
//	                try{
//	                	sendEmailAboutChangingPassword(userName,((Textbox)this.getFellow("password")).getText());
//					} catch (Exception e) {
//	                    logger.error("#onAdminChangePassword: Error on sending email about changing password", e);
//	                }
			}
			} catch(Exception e) {
				Messagebox.show(e.getMessage(), Labels.getLabel("userChangePwd.error"), Messagebox.OK, Messagebox.ERROR);
			}
			this.detach();
		}
	}
	
	private boolean isPasswordAndConfirmationCorrect() throws Exception {
		Textbox password = (Textbox)this.getFellow("password");
		Textbox confirmation = (Textbox)this.getFellow("confirmation");
		if (password.getValue().trim().length()==0 || 
			confirmation.getValue().trim().length()==0) {
			Messagebox.show(Labels.getLabel("userChangePwd.error.passwNotSpec"), Labels.getLabel("userChangePwd.error"), Messagebox.OK, Messagebox.ERROR);
			return false;
		} else if (!password.getValue().equals(confirmation.getValue())) {			
			Messagebox.show(Labels.getLabel("userChangePwd.error.passNotSame"), Labels.getLabel("userChangePwd.error"), Messagebox.OK, Messagebox.ERROR);
			return false;
		} 
		return true;
	}
	
	public void onUserChangePassword() throws Exception {
		if (!usersService.isPasswordCorrect(userName, ((Textbox)this.getFellow("oldPassword")).getText())) {
			Messagebox.show(Labels.getLabel("userChangePwd.error.oldPassWrong"), Labels.getLabel("userChangePwd.error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if (isPasswordAndConfirmationCorrect()) {
			try {
//				boolean changed = 
					usersService.changePassword(userName, ((Textbox)this.getFellow("password")).getText());
//				if (changed){
//		                try{
//		                	sendEmailAboutChangingPassword(userName,((Textbox)this.getFellow("password")).getText());
//						} catch (Exception e) {
//		                    logger.error("#onUserChangePassword: Error on sending email about changing password", e);
//		                }
//				}
			} catch(Exception e) {
				Messagebox.show(e.getMessage(), Labels.getLabel("userChangePwd.error"), Messagebox.OK, Messagebox.ERROR);
			}
			this.detach();
		}
	}

//	private void sendEmailAboutChangingPassword(String userName, String newPassword) throws Exception {
//		Users user = usersService.getUser(userName);
//        String account = userName;
//        String password =  newPassword;
//        String email =  user.getEmail();
//        String name =  user.getName();
//        String surname =  user.getSurname();
//
//        //sending info mail to user with changed password
//        List<String> textParameters = new ArrayList<String>();
//        textParameters.add(account);
//        textParameters.add(email);
//        textParameters.add(name + " " + surname);
//        textParameters.add(password);
//        mailService.sendChangedPasswordEmail(new InternetAddress(email, name + " " + surname), textParameters);
//	}
}
