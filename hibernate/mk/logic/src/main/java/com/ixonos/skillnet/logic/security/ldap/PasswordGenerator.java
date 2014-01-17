package com.ixonos.skillnet.logic.security.ldap;

import org.springframework.security.providers.encoding.MessageDigestPasswordEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: May 21, 2009
 * Time: 12:02:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PasswordGenerator {

    /**
     * Random password generator by system time and saltSource
     * @param saltSource
     * @param length
     * @return
     */
    public static String getPassword(final String saltSource,final  int length) {
        String time = (new Long(System.nanoTime())).toString();
        MessageDigestPasswordEncoder mdpe = new MessageDigestPasswordEncoder("SHA-256");
        String generatedPassword = mdpe.encodePassword(time, saltSource);
        return generatedPassword.subSequence(0,length).toString().toUpperCase();
    }

//    public static void main(String[] args) {
//        PasswordGenerator.getPassword("hustasl", 10);
//    }

}
