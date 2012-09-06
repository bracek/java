package sk.mka.phd.velocity.data.file;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public interface IFileLoadData {

    /**
     * 
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public DataInputStream openDataInputStream(final String filename) throws FileNotFoundException;

    /**
     *
     * @param filename
     * @return 
     * @returna array of array
     * @throws IOException
     */
    public Object[][] loadDataAsObjects(String filename) throws IOException;

    /**
     *
     * @param filename
     * @return content of file, where file is represent by following line. Each line constist of: key=value
     * @throws IOException
     */
    String[][] getFileContent(String filename) throws IOException;
}
