package sk.mka.phd.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.tools.file.ReadFileSingleton;

/**
 *
 * @author katrami
 */
public class AbstractLoadData implements ILoadData {

    protected String separator = null;
    protected int rows = 0;
    protected int cols = 0;

    @Override
    public String[][] loadData(String filename) throws IOException {
        return ReadFileSingleton.getInstance().loadData(filename, getSeparator());
    }


      @Override
    public DataInputStream openDataInputStream(final String filename) throws FileNotFoundException {
        // Open the file that is the first
        // command line parameter
        FileInputStream fstream = new FileInputStream(filename);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        return in;
    }

      /**
     *
     * @param aFile
     * @return
     */
    @Override
    public String getFileContent(final File aFile) {

        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            try {
                String line = null; //not declared within while loop
        /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }


    public String getSeparator() {
        if (separator == null) {
            setSeparator(Const.EQUALS_SEPARATOR);
        }
        return separator;
    }

    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
