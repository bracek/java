package com.ixonos.skillnet.logic.security.ldap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 12, 2009
 * Time: 5:51:37 PM
 * To change this template use File | Settings | File Templates.
 */
//FIXME: to be removed later - it is just a sample for LDAP Client
// DO NOT Remove it !!!! not a sample yet
public class LdapClient {

    /**
     * logger for current class
     */
    private final Logger logger = Logger.getLogger(LdapClient.class);
    
    /**
     * Returns the list of users values from LDAP.
     * @param searchExpression expressin like "OU=Kosice,OU=Foreign,OU=People".
     * @param ldapProviderUrl ldap url.
     * @param ldapSecurityAuthentication authentication method, like "simple".
     * @param ldapManagerPrincipal user manager, like: "CN=Peter Obluda,OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local".
     * @param ldapManagerPassword password.
     * @return List of strings, each strings like: "CN=Marek Menhert,OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local";
     **/
	@SuppressWarnings("unchecked")
	public List<String> getUsersFullNameList(final String searchExpression,final  String ldapProviderUrl,final  String ldapSecurityAuthentication,final  String ldapManagerPrincipal,final  String ldapManagerPassword) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapProviderUrl);
        env.put(Context.SECURITY_AUTHENTICATION, ldapSecurityAuthentication);
        env.put(Context.SECURITY_PRINCIPAL, ldapManagerPrincipal);
        env.put(Context.SECURITY_CREDENTIALS, ldapManagerPassword);
        DirContext ctx = null;
        NamingEnumeration results = null;
        
        List<String> users = new ArrayList<String>(); 
        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(searchExpression, "(objectclass=person)", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                users.add(searchResult.getNameInNamespace());
                //Attributes attributes = searchResult.getAttributes();
                logger.debug("have LDAP name:"+searchResult.getNameInNamespace());
            }            
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                	logger.warn("Cannot close LDAP results, due:"+e.getMessage());
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                	logger.error("Cannot read from LDAP, due:"+e.getMessage());
                }
            }
        }
		
		return users;
	}
    	
    public List<Map<String,Object>> findUsersByCN(final String searchExpression,final  String ldapProviderUrl,final  String ldapSecurityAuthentication,final  String ldapManagerPrincipal,final  String ldapManagerPassword) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapProviderUrl /*"ldap://hkidc01:389/dc=ixonos,dc=local"*/ );
        env.put(Context.SECURITY_AUTHENTICATION, ldapSecurityAuthentication /*"simple"*/ );
        env.put(Context.SECURITY_PRINCIPAL, ldapManagerPrincipal /*"CN=Slavomir Hustaty,OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local"*/ );
        env.put(Context.SECURITY_CREDENTIALS, ldapManagerPassword /*"password"*/ );
        DirContext ctx = null;
        NamingEnumeration results = null;

        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();

        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("OU=People", "(&(distinguishedName=" + searchExpression + ")(objectClass=person))", controls);
            /*"OU=Kosice,OU=Foreign,OU=People"*/
//                results = ctx.search("CN=Slavomir Hustaty,OU=Kosice,OU=Foreign,OU=People", "(objectclass=person)", controls); -- exact user search
//                results = ctx.search("CN=esimiehet,OU=Roles,OU=People", "(objectclass=group)", controls); -- managers in Ixonos
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();

                Map<String, Object> userAttributesToInsert = new HashMap<String, Object>();

                userAttributesToInsert.put("firstName", attributes.get("givenName").get());
                userAttributesToInsert.put("lastName", attributes.get("sn").get());
                userAttributesToInsert.put("email", attributes.get("mail").get());
                userAttributesToInsert.put("phone", attributes.get("telephoneNumber").get());
                userAttributesToInsert.put("distinguishedName", attributes.get("distinguishedName").get());
                userAttributesToInsert.put("description", attributes.get("description").get());
                userAttributesToInsert.put("plainPassword", PasswordGenerator.getPassword((String)userAttributesToInsert.get("email"),10));
                userAttributesToInsert.put("account", attributes.get("sAMAccountName").get());
//                    userAttributesToInsert.put("title", attributes.get("title").get());
                //userAttributesToInsert.put("department", attributes.get("department").get());

//                    NamingEnumeration<String> attributesEnum = attributes.getIDs();
//                    while(attributesEnum.hasMore()) {
//                        String attributeName =attributesEnum.next();
//                        Attribute attribute = attributes.get(attributeName);
//                        System.out.println(  attributeName + "  = " + attribute.get());
//                    }
//                    Attribute attr = attributes.get("cn");
//                    String cn = (String) attr.get();
//                    System.out.println(" Person Common Name = " + cn);
                logger.debug(userAttributesToInsert);
                resultList.add(userAttributesToInsert);
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                }
            }
        }
        return resultList;
    }

    @Deprecated
    public boolean importUserByCN(final String cn) {
        String ldapProviderUrl = "ldap://hkidc01:389/dc=ixonos,dc=local";
        String ldapSecurityAuthentication = "simple";
        String ldapManagerPrincipal = "CN=Slavomir Hustaty,OU=Kosice,OU=Foreign,OU=People,DC=ixonos,DC=local";
        String ldapManagerPassword = "password";
        List<Map<String,Object>> foundUsers = findUsersByCN(cn,ldapProviderUrl,ldapSecurityAuthentication,ldapManagerPrincipal,ldapManagerPassword);

        return true;
    }

}
