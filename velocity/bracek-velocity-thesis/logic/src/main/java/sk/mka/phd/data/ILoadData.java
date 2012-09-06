package sk.mka.phd.data;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author katrami
 */
public interface ILoadData {

    String[][] loadData(String filename) throws IOException;

    void setSeparator(String separator);

    /**
     *
     * @param aFile
     * @return
     */
    String getFileContent(final File aFile);

    DataInputStream openDataInputStream(final String filename) throws FileNotFoundException;
}
