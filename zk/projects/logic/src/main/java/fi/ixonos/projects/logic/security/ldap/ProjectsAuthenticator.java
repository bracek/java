/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ixonos.projects.logic.security.ldap;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.enumeration.ProjectsRole;
import fi.ixonos.projects.logic.service.UsersService;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.Authentication;
import org.springframework.security.providers.ldap.LdapAuthenticator;

/**
 *
 * @author polakja
 */
public class ProjectsAuthenticator implements LdapAuthenticator {

    private static final Logger logger = Logger.getLogger(ProjectsAuthenticator.class);

    private static final String LDAP_DefaultUSFilter = "(final objectclass=person)";
    private static final String LDAP_DefaultUSFormat = "CN={0},OU=Kosice,OU=Foreign,OU=People";
    private static final String LDAP_DefaultDNFormat = "CN={0},OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local";

    private final UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");

    private final ContextSource contextSource;

    public ProjectsAuthenticator(final ContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Override
    public DirContextOperations authenticate(final Authentication authentication) {
        NamingEnumeration results = null;
        DirContext ctx = null;
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("config-logic.properties");

            String distinguishedNameFormat = props.getProperty("projects.ldap.format.distinguishedName", LDAP_DefaultDNFormat);
            String userSearchFormat = props.getProperty("projects.ldap.format.userSearchName", LDAP_DefaultUSFormat);
            String userSearchFilter = props.getProperty("projects.ldap.userSearchFilter", LDAP_DefaultUSFilter);

            String principal = (String) authentication.getPrincipal();
            String userCN = null;
            if(usersService.isUserAlreadyRegistered(principal)) { // in case of 'polakja' login - after 1st logon
                Users user = usersService.getUser(principal);
                userCN = user.getName() + " " + user.getSurname();
            } else { // in case of 'Jan Polak' login - in 1st logon or fallback after 1st logon
                userCN = principal;
            }
            String userDN = MessageFormat.format(distinguishedNameFormat, userCN);
            ctx = contextSource.getContext(userDN, (String) authentication.getCredentials());
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(MessageFormat.format(userSearchFormat, userCN), userSearchFilter, controls);
            if (results.hasMore()) {
                SearchResult result = (SearchResult) results.next();
                DirContextAdapter adapter = new DirContextAdapter(result.getAttributes(), new DistinguishedName(result.getNameInNamespace()));
                String accountName = (String) adapter.getAttributes().get("sAMAccountName").get();
                logger.debug(MessageFormat.format("LDAP authentication successful: Welcome user {0}", accountName));

                logger.debug("Importing Logged User '" + accountName + "'.");
                String[] names = ((String)adapter.getAttributes().get("name").get()).split(" ");
                String email = (String) getOptionalAttribute(adapter.getAttributes(), "mail");
                String phone = (String) getOptionalAttribute(adapter.getAttributes(), "telephoneNumber");
                String position = (String) getOptionalAttribute(adapter.getAttributes(), "description");
                String location = (String) getOptionalAttribute(adapter.getAttributes(), "l");
                boolean isGroupManager = adapter.getAttributes().get("directReports") != null;
                if(!usersService.isUserAlreadyRegistered(accountName)) {
                    if(isGroupManager)
                        usersService.addNewUser(accountName, "", names[0], names[names.length-1], email, phone, location, position, true, null, Arrays.asList(new String[]{ProjectsRole.ROLE_GM, ProjectsRole.ROLE_ADMIN}));
                    else
                        usersService.addNewUser(accountName, "", names[0], names[names.length-1], email, phone, location, position, true, null, Arrays.asList(new String[]{ProjectsRole.ROLE_USER}));
                }
                logger.debug("User '" + accountName + "' successfully imported/updated. Password is [LDAP PASSWORD]");

                return adapter;
            } else {
                throw new NamingException(MessageFormat.format("LDAP authentication failed: Unknown user {0}", userDN));
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                    logger.error(MessageFormat.format("Cannot close LDAP results, due: {0}", e.getMessage()));
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    logger.error(MessageFormat.format("Cannot read from LDAP, due: {0}", e.getMessage()));
                }
            }
        }
    }

    private Object getOptionalAttribute(final Attributes attrs,final  String attrName) throws NamingException {
        return attrs.get(attrName) != null ? attrs.get(attrName).get() : "";
    }
}
