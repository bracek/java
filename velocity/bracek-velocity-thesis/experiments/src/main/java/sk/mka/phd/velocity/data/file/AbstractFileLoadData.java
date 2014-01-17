package sk.mka.phd.velocity.data.file;

import sk.mka.phd.data.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.SeparatorConst;
import sk.mka.phd.tools.file.ReadFileSingleton;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public abstract class AbstractFileLoadData extends AbstractLoadData implements IFileLoadData {

    protected String filename;
    protected final ArrayList numbersList = new ArrayList();
    protected final ArrayList listOfObjects = new ArrayList();
    private static Logger log = Logger.getLogger(AbstractFileLoadData.class);
    private String[][] values;
    private final List<String> phonemaArrayList = new ArrayList<String>();

    public List loadPhonemaList(final String filename) {
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
                log.debug("phonema: " + strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split(SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue());
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        phonemaArrayList.add(subStr[i]);
                    }
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            log.fatal("FATAL" + e.getMessage());
        }
        log.info("numbersList = " + numbersList);
        return phonemaArrayList;
    }

    @Override
    public String[][] getFileContent(final String filename) throws IOException {
        String[][] numbers;
        if (ReadFileSingleton.getInstance().getValues() == null) {
            numbers = ReadFileSingleton.getInstance().loadData(filename, getSeparator());
        } else {
            numbers = ReadFileSingleton.getInstance().getValues();
        }
        return numbers;
    }

  

    @Override
    public Object[][] loadDataAsObjects(final String filename) throws IOException {
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
                    subStr = strLine.split(getSeparator());
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
    protected void splitLine(final String strLine,final  String[] subStr) {
        if (!strLine.equals("")) {
            subStr = strLine.split(getSeparator());
            cols = subStr.length;
            for (int i = 0; i < subStr.length; i++) {
                listOfObjects.add(subStr[i]);
            }
        }
    }

    public String[][] getValues() {
        return values;
    }

    public void setValues(final String[][] values) {
        this.values = values;
    }
}
