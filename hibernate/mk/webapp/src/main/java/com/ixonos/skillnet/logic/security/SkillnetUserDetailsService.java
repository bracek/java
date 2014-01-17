package com.ixonos.skillnet.logic.security;

import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.jdbc.JdbcDaoImpl;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 4, 2009
 * Time: 8:58:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillnetUserDetailsService extends JdbcDaoImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(final String username) {
        UserDetails userDetails = super.loadUserByUsername(username);
        SkillnetUser skillnetUser = new SkillnetUser(userDetails);
        return skillnetUser;
    }
    
}
