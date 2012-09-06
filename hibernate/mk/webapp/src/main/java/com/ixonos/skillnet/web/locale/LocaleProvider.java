package com.ixonos.skillnet.web.locale;

import java.util.Locale;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.zkoss.web.Attributes;

public class LocaleProvider implements org.zkoss.zk.ui.util.RequestInterceptor {
    public void request(org.zkoss.zk.ui.Session sess, Object request, Object response) {
    	
    	String localeAttr = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;
        Locale currentLocale = (Locale)sess.getAttribute(localeAttr);
        sess.setAttribute(Attributes.PREFERRED_LOCALE, currentLocale);
    }
}
