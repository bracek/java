package fi.ixonos.projects.logic.security.ldap;

import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.UsersService;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 * @author polakja
 */
public class ImportLdapUsersService {

    /**
     * logger for current class
     */
    private static final Logger logger = Logger.getLogger(ImportLdapUsersService.class);
    private static final String LDAP_DefaultUSFilter = "(final objectclass=person)";
    private static final String LDAP_DefaultSFormat = "OU=Kosice,OU=Foreign,OU=People";
    private static final String LDAP_DefaultDNFormat = "CN={0},OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local";
    private final UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");
    private final ContextSource contextSource;

    public ImportLdapUsersService(final ContextSource contextSource) {
        this.contextSource = contextSource;
    }

    /**
     * Returns the list of users values from LDAP.
     * @param ldapManagerName user name, like: "Peter Obluda" or "obludpe".
     * @param ldapManagerPassword password.
     * @return List of strings, each strings like: "CN=Marek Menhert,OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local";
     **/
    @SuppressWarnings("unchecked")
    public List<String> getUsersFullNameList(final String principal,
final  String ldapManagerPassword) {
        NamingEnumeration results = null;
        DirContext ctx = null;
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("config-logic.properties");

            String distinguishedNameFormat = props.getProperty("projects.ldap.format.distinguishedName", LDAP_DefaultDNFormat);
            String userSearchFilter = props.getProperty("projects.ldap.userSearchFilter", LDAP_DefaultUSFilter);
            String usersSearchFormat = props.getProperty("projects.ldap.userSearch", LDAP_DefaultSFormat);

            String userCN = null;
            if (usersService.isUserAlreadyRegistered(principal)) { // in case of 'polakja' login - after 1st logon
                Users user = usersService.getUser(principal);
                userCN = user.getName() + " " + user.getSurname();
            } else { // in case of 'Jan Polak' login - in 1st logon or fallback after 1st logon
                userCN = principal;
            }
            String userDN = MessageFormat.format(distinguishedNameFormat, userCN);
            ctx = contextSource.getContext(userDN, ldapManagerPassword);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(usersSearchFormat, userSearchFilter, controls);

            List<String> users = new ArrayList<String>();
            while (results.hasMore()) {
                SearchResult result = (SearchResult) results.next();
                users.add(result.getNameInNamespace());
                logger.debug("User LDAP name:" + result.getNameInNamespace());
            }
            return users;
        } catch (NamingException e) {
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

    public List<Map<String, Object>> findUsersByCN(final String searchExpression,
final  String principal,
final  String ldapManagerPassword) {
        NamingEnumeration results = null;
        DirContext ctx = null;
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("config-logic.properties");

            String distinguishedNameFormat = props.getProperty("projects.ldap.format.distinguishedName", LDAP_DefaultDNFormat);

            String userCN = null;
            if (usersService.isUserAlreadyRegistered(principal)) { // in case of 'polakja' login - after 1st logon
                Users user = usersService.getUser(principal);
                userCN = user.getName() + " " + user.getSurname();
            } else { // in case of 'Jan Polak' login - in 1st logon or fallback after 1st logon
                userCN = principal;
            }
            String userDN = MessageFormat.format(distinguishedNameFormat, userCN);
            ctx = contextSource.getContext(userDN, ldapManagerPassword);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("OU=People", "(&(distinguishedName=" + searchExpression + ")(objectClass=person))", controls);

            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            while (results.hasMore()) {
                SearchResult result = (SearchResult) results.next();
                Attributes attributes = result.getAttributes();

                Map<String, Object> userAttributesToInsert = new HashMap<String, Object>();

                try {
                    // required attributes
                    String[] names = ((String) attributes.get("name").get()).split(" ");
                    userAttributesToInsert.put("firstName", names[0]);
                    userAttributesToInsert.put("lastName", names[names.length - 1]);
                    userAttributesToInsert.put("distinguishedName", attributes.get("distinguishedName").get());
                    userAttributesToInsert.put("account", attributes.get("sAMAccountName").get());
                    // optional attributes
                    userAttributesToInsert.put("email", getOptionalAttribute(attributes, "mail"));
                    userAttributesToInsert.put("phone", getOptionalAttribute(attributes, "telephoneNumber"));
                    userAttributesToInsert.put("position", getOptionalAttribute(attributes, "description"));
                    userAttributesToInsert.put("plainPassword", ""); // LDAP password used
                    userAttributesToInsert.put("location", getOptionalAttribute(attributes, "l"));
                    userAttributesToInsert.put("isGroupManager", attributes.get("directReports") != null);
                } catch (NullPointerException e) {
                    logger.error(MessageFormat.format("Error - Probably some required LDAP attribute is empty or invalid for user '{0}'", userAttributesToInsert.get("distinguishedName")));
                    throw e;
                }

                logger.debug(userAttributesToInsert);
                resultList.add(userAttributesToInsert);
            }
            return resultList;
        } catch (NamingException e) {
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

    private Object getOptionalAttribute(final Attributes attrs,
final  String attrName) throws NamingException {
        return attrs.get(attrName) != null ? attrs.get(attrName).get() : "";
    }
}
