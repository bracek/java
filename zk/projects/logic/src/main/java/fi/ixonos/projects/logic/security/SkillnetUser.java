package fi.ixonos.projects.logic.security;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.UsersService;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.GrantedAuthority;
import org.apache.log4j.Logger;

import java.util.HashMap;
import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 4, 2009
 * Time: 9:00:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillnetUser extends User {

    private String firstName;
    private String lastName;
    private String email;
    private Users manager;
    private HashMap<Object, Object> customUserDetails;

    @Resource(name = "usersService")
    private UsersService usersService;

    /**
     * logging
     */
    private final Logger logger = Logger.getLogger(SkillnetUser.class);

    @Deprecated
    public SkillnetUser(final String username,
                        final String password,
                        final boolean enabled,
                        final GrantedAuthority[] authorities)
            throws IllegalArgumentException {
        this(username, password, enabled, true, true, authorities);
        logger.debug("deprecated constructor");
    }

    public SkillnetUser(final UserDetails userDetails) {
        this(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), userDetails.isAccountNonLocked(), userDetails.getAuthorities());
        logger.debug("creating SkillnetUser from UserDetails:" + userDetails);
    }

    public SkillnetUser(final String username,
                        final String password,
                        final boolean enabled,
                        final boolean accountNonExpired,
                        final boolean credentialsNonExpired,
                        final GrantedAuthority[] authorities)
            throws IllegalArgumentException {
        this(username, password, enabled, accountNonExpired, credentialsNonExpired, true, authorities);
        logger.debug("constructor");
    }

    public SkillnetUser(final String username,
                        final String password,
                        final boolean enabled,
                        final boolean accountNonExpired,
                        final boolean credentialsNonExpired,
                        final boolean accountNonLocked,
                        final GrantedAuthority[] authorities)
            throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        logger.debug("constructor");
        try {
            //FIXME: why is UsersService not wired as resource? because it doesn't have Spring annotation @Component or @Service
            if (usersService == null)
                usersService = (UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");
            Users user = usersService.getUser(username);
            this.firstName = user.getName();
            this.lastName = user.getSurname();
            this.email = user.getEmail();
            this.manager = user.getManager();
            this.customUserDetails = new HashMap<Object, Object>();
        } catch (Exception ex) {
            logger.error("UsersService was not able to find user entry for username: '" + username + "'");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Users getManager() {
        return manager;
    }

    public void setManager(final Users manager) {
        this.manager = manager;
    }

    public HashMap<Object, Object> getCustomUserDetails() {
        return customUserDetails;
    }

    public void setCustomUserDetails(final HashMap<Object, Object> customUserDetails) {
        this.customUserDetails = customUserDetails;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("; customUserDetails: ").append(customUserDetails);
        sb.append("; manager: ").append(manager);
        //TODO do we need more info ?
        return sb.toString();
    }
}
