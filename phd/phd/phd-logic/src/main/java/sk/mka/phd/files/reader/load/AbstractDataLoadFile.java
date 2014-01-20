package sk.mka.phd.files.reader.load;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 *
 * @author bracek
 * @date Jun 29, 2010
 */
public abstract class AbstractDataLoadFile {

    protected final static Logger log = Logger.getLogger(AbstractDataLoadFile.class);
    protected String finalPath;
    protected String separator;

    public AbstractDataLoadFile(final String finalPath,
 final String separator) {
        this.finalPath = finalPath;
        this.separator = separator;
    }

    final protected void handleInput() {
        if (finalPath == null) {
            try {
                throw new Exception("Fatal, missig finalPath");
            } catch (Exception ex) {
                log.debug(ex);
            }
        }
        if (separator == null) {
            try {
                throw new Exception("Fatal, missig separator");
            } catch (Exception ex) {
                log.debug(ex);
            }
        }
    }

    protected final DataInputStream openInputStream() throws FileNotFoundException {
        // Open the file that is the first
        // command line parameter
        FileInputStream fstream = new FileInputStream(finalPath);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        return in;
    }
}
