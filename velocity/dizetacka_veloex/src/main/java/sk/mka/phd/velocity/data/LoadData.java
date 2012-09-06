package sk.mka.phd.velocity.data;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public interface LoadData {

    public DataInputStream openDataInputStream(final String filename) throws FileNotFoundException;

    /**
     * 
     * @param filename
     * @return array of array from filename, where in each line is used tabulator separator
     * @throws IOException
     */
    public String[][] loadData(String filename) throws IOException;

    /**
     *
     * @param filename
     * @return 
     * @returna array of array
     * @throws IOException
     */
    public Object[][] loadDataAsObjects(String filename) throws IOException;
}
