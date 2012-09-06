package sk.mka.phd.velocity.data;

import sk.mka.phd.velocity.data.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public abstract class AbstractLoadData implements LoadData {

    protected String filename;
    protected int rows;
    protected int cols;
    protected final ArrayList numbersList = new ArrayList();
    protected final ArrayList listOfObjects = new ArrayList();
    private static Logger log = Logger.getLogger(AbstractLoadData.class);

    @Override
    public DataInputStream openDataInputStream(final String filename) throws FileNotFoundException {
        // Open the file that is the first
        // command line parameter
        FileInputStream fstream = new FileInputStream(filename);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        return in;
    }

    @Override
    public String[][] loadData(String filename) throws IOException {
        String[] subStr;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            rows = 0;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                log.debug(strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split("\t");
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
        String[][] numbers = new String[getRows()][getCols()];
        int row = 0;
        int col = 0;
        Object x;
        for (int i = 0; i < numbersList.size(); i++) {
            x = numbersList.get(i);
            if (i != 0) {
                if ((i % getCols()) == 0) {
                    row++;
                    col = 0;
                }
            }
            numbers[row][col] = (String) x;
            col++;
        }

        return numbers;
    }

    @Override
    public Object[][] loadDataAsObjects(String filename) throws IOException {
        this.filename = filename;
        String[] subStr;
        try {
            // Get the object of DataInputStream
            DataInputStream in = openDataInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            rows = 0;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                log.debug(strLine);
                if (!strLine.isEmpty()) {
                    subStr = strLine.split("\t");
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        listOfObjects.add(subStr[i].replace(" ", ""));
                    }
                rows++;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
            log.debug(e.getMessage());
        }

        log.debug("listOfObjects = " + listOfObjects);

        Object[][] data = new Object[getRows()][getCols()];
        int row = 0;
        int col = 0;
        String x = null;
        for (int i = 0;
                i < listOfObjects.size();
                i++) {
            if (listOfObjects.get(i) instanceof String) { //has no sence
                x = (String) listOfObjects.get(i);
            }

            if (i != 0) {
                if ((i % getCols()) == 0) {
                    row++;
                    col = 0;
                }
            }
            data[row][col] = x.toString();
            col++;
        }
        for (int i = 0;
                i < getRows();
                i++) {
            for (int j = 0; j < getCols(); j++) {
//                System.out.println("numbers[" + i + "][" + j + "]:=" + data[i][j]);
                log.debug("numbers[" + i + "][" + j + "]:=" + data[i][j]);
            }
        }
        return data;
    }

    /**
     * split line to substring, which are separete by tabulator
     * @param strLine
     * @param subStr
     */
    protected void splitLine(String strLine, String[] subStr) {
        if (!strLine.equals("")) {
            subStr = strLine.split("\t");
            cols = subStr.length;
            for (int i = 0; i < subStr.length; i++) {
                listOfObjects.add(subStr[i]);
            }
        }
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }
}
