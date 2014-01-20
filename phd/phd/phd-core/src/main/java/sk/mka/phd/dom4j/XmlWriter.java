
package sk.mka.phd.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public class XmlWriter {

    protected static Logger log = Logger.getLogger(XmlWriter.class);

      public static void writeXmlDocumentToFile(final String xmlPath,
final  Document document) {
        try {
            XMLWriter output = new XMLWriter(new FileWriter(new File(xmlPath)));
            output.write(document);
            output.close();
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }
}
