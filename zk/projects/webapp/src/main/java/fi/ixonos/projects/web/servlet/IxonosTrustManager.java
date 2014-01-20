/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ixonos.projects.web.servlet;

import java.security.cert.CertificateException;
import java.text.MessageFormat;
import org.apache.log4j.Logger;

/**
 * Helper class for enabling SSL session. Tests whether at least one 
 * server certificate issuer name matches <code>CN=Ixonos Root CA, DC=Ixonos, DC=local</code>.
 * Otherwise it throws <code>java.security.cert.CertificateException</code>.
 * <br/>
 * Note: May be replaced by acceptance of server certificate. It can be done through Java <code>keytool</code> utility.
 * @author polakja
 */
public class IxonosTrustManager implements javax.net.ssl.X509TrustManager {

    private static Logger logger = Logger.getLogger(ProjectsInitServlet.class);
    private static final String trustedIssuer = "CN=Ixonos Root CA, DC=Ixonos, DC=local";

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkClientTrusted(final java.security.cert.X509Certificate[] certs,
final  String authType) throws CertificateException {
        // do nothing
    }

    @Override
    public void checkServerTrusted(final java.security.cert.X509Certificate[] certs,
final  String authType) throws CertificateException {
        boolean trusted = false;
        for (java.security.cert.X509Certificate c : certs) {
            logger.info(MessageFormat.format("Check server trust: {0} - {1}", authType, c.getIssuerDN().getName()));
            if (trustedIssuer.equals(c.getIssuerDN().getName())) {
                logger.info(MessageFormat.format("Server certificate issuer {0} is trusted.", c.getIssuerDN().getName()));
                trusted = true;
            }
        }
        if (!trusted) {
            throw new CertificateException("Untrusted server chain");
        }
    }
}
