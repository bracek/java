/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.ixonos.projects.logic.security.ldap;

import java.text.MessageFormat;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.security.userdetails.ldap.UserDetailsContextMapper;

/**
 *
 * @author polakja
 */
public class ProjectsDetailsContextMapper implements UserDetailsContextMapper {

    private static final Logger logger = Logger.getLogger(ProjectsDetailsContextMapper.class);
    private UserDetailsService userDetailsService;

    public ProjectsDetailsContextMapper(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDetails mapUserFromContext(final DirContextOperations ctx,
final  String username,
final  GrantedAuthority[] authority) {
        try {
            String accountName = (String) ctx.getAttributes().get("sAMAccountName").get();
            logger.debug(MessageFormat.format("Loading user details for account: {0}", accountName));
            return this.userDetailsService.loadUserByUsername(accountName);
        } catch (NamingException ex) {
            throw new UsernameNotFoundException("Failed to find account name in cached context", ex);
        }
    }

    @Override
    public void mapUserToContext(final UserDetails user,
final  DirContextAdapter ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
