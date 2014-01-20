/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.ixonos.projects.logic.security.ldap;

import org.springframework.security.ldap.LdapAuthoritiesPopulator;
import org.springframework.security.providers.ldap.LdapAuthenticationProvider;
import org.springframework.security.providers.ldap.LdapAuthenticator;
import org.springframework.security.userdetails.UserDetailsService;

/**
 *
 * @author polakja
 */
public class ProjectsAuthenticationProvider extends LdapAuthenticationProvider {

    public ProjectsAuthenticationProvider(final LdapAuthenticator authenticator,
final  LdapAuthoritiesPopulator authoritiesPopulator) {
        super(authenticator, authoritiesPopulator);
    }

    public ProjectsAuthenticationProvider(final LdapAuthenticator authenticator) {
        super(authenticator);
    }

    public void setUsersDetailsService(final UserDetailsService usersDetailService) {
        this.setUserDetailsContextMapper(new ProjectsDetailsContextMapper(usersDetailService));
    }

    public UserDetailsService getUsersDetailsService() {
        throw new UnsupportedOperationException("not supported");
    }

}
