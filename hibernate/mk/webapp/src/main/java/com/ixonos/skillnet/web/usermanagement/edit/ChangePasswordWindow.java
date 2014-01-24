package com.ixonos.skillnet.web.usermanagement.edit;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.MailService;
import com.ixonos.skillnet.logic.service.UsersService;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

public class ChangePasswordWindow extends Window {

    /**
     * Log4j logger for this class
     */
    private static Logger logger = Logger.getLogger(ChangePasswordWindow.class);
    private static final long serialVersionUID = 1L;
    private final MailService mailService = (MailService) SpringUtil
            .getApplicationContext().getBean("mailService");
    private final UsersService usersService = (UsersService) SpringUtil
            .getApplicationContext().getBean("usersService");
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
                final boolean changed = usersService.changePassword(userName,
                        ((Textbox) this.getFellow("password")).getText());
                if (changed) {
                    try {
                        sendEmailAboutChangingPassword(userName,
                                ((Textbox) this.getFellow("password"))
                                        .getText());
                    } catch (final Exception e) {
                        logger.error(
                                "#onAdminChangePassword: Error on sending email about changing password",
                                e);
                    }
                }
            } catch (final Exception e) {
                Messagebox.show(e.getMessage(),
                        Labels.getLabel("userChangePwd.error"), Messagebox.OK,
                        Messagebox.ERROR);
            }
            this.detach();
        }
    }

    private boolean isPasswordAndConfirmationCorrect() throws Exception {
        final Textbox password = (Textbox) this.getFellow("password");
        final Textbox confirmation = (Textbox) this.getFellow("confirmation");
        if (password.getValue().trim().length() == 0
                || confirmation.getValue().trim().length() == 0) {
            Messagebox.show(
                    Labels.getLabel("userChangePwd.error.passwNotSpec"),
                    Labels.getLabel("userChangePwd.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return false;
        } else if (!password.getValue().equals(confirmation.getValue())) {
            Messagebox.show(Labels.getLabel("userChangePwd.error.passNotSame"),
                    Labels.getLabel("userChangePwd.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return false;
        }
        return true;
    }

    public void onUserChangePassword() throws Exception {
        if (!usersService.isPasswordCorrect(userName,
                ((Textbox) this.getFellow("oldPassword")).getText())) {
            Messagebox.show(
                    Labels.getLabel("userChangePwd.error.oldPassWrong"),
                    Labels.getLabel("userChangePwd.error"), Messagebox.OK,
                    Messagebox.ERROR);
            return;
        }
        if (isPasswordAndConfirmationCorrect()) {
            try {
                // boolean changed =
                usersService.changePassword(userName,
                        ((Textbox) this.getFellow("password")).getText());
                // if (changed){
                // try{
                // sendEmailAboutChangingPassword(userName,((Textbox)this.getFellow("password")).getText());
                // } catch (Exception e) {
                // logger.error("#onUserChangePassword: Error on sending email about changing password",
                // e);
                // }
                // }
            } catch (final Exception e) {
                Messagebox.show(e.getMessage(),
                        Labels.getLabel("userChangePwd.error"), Messagebox.OK,
                        Messagebox.ERROR);
            }
            this.detach();
        }
    }

    private void sendEmailAboutChangingPassword(final String userName,
                                                final String newPassword) throws Exception {
        final Users user = usersService.getUser(userName);
        final String account = userName;
        final String password = newPassword;
        final String email = user.getEmail();
        final String name = user.getName();
        final String surname = user.getSurname();

        // sending info mail to user with changed password
        final List<String> textParameters = new ArrayList<String>();
        textParameters.add(account);
        textParameters.add(email);
        textParameters.add(name + " " + surname);
        textParameters.add(password);
        mailService.sendChangedPasswordEmail(new InternetAddress(email, name
                + " " + surname), textParameters);
    }
}
