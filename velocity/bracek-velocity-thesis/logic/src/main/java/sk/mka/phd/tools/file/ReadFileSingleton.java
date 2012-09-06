package sk.mka.phd.tools.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author katrami
 */
public class ReadFileSingleton {

    private static ReadFileSingleton _instance;
    private static Logger log = Logger.getLogger(ReadFileSingleton.class);
    private ArrayList numbersList = new ArrayList();
    private String[][] values;

    private ReadFileSingleton() {
    }

    public static synchronized ReadFileSingleton getInstance() {
        if (_instance == null) {
            _instance = new ReadFileSingleton();
        }
        return _instance;
    }

    public String[][] loadData(String filename, String separator) throws IOException {
        String[] subStr;
        int cols = 0;
        int rows = 0;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line

            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                log.debug(strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split(separator);
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        numbersList.add(subStr[i]);
                    }
                }
                if (!strLine.equals("")) {
                    rows++;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
            log.debug("Error: " + e.getMessage());

        }
        values = new String[rows][cols];
        int row = 0;
        int col = 0;
        Object x;
        for (int i = 0; i < numbersList.size(); i++) {
            x = numbersList.get(i);
            if (i != 0) {
                if ((i % cols) == 0) {
                    row++;
                    col = 0;
                }
            }
            values[row][col] = (String) x;
            col++;
        }

        return values;
    }

    public String[][] getValues() {
        return values;
    }

    public void setValues(String[][] values) {
        this.values = values;
    }
}
