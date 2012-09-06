package fi.ixonos.projects.web.usermanagement.ldap;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * @author polakja
 *
 */
public class AuthenticationWindow extends Window {

    private static final long serialVersionUID = 1L;
    private boolean canceled;
    private String name;
    private String password;

    public void continueLoad(Event event) throws Exception {
        canceled = false;
        name = ((Textbox) this.getFellow("name")).getValue();
        password = ((Textbox) this.getFellow("password")).getValue();
        this.detach();
    }

    public void cancelLoad(Event event) throws Exception {
        canceled = true;
        this.detach();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
        ((Textbox) this.getFellow("name")).setValue(name);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the canceled
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * @param canceled the canceled to set
     */
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}
