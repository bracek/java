package sk.mka.phd.files.reader.load.square;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sk.mka.phd.files.reader.load.AbstractDataLoadFile;
import sk.mka.phd.files.reader.load.LoadData;

/**
 *
 * @author bracek
 * @date Jun 29, 2010
 */
public abstract class AbstractSquareDataLoadFile extends AbstractDataLoadFile implements LoadData, ISquareLoadDataFile {

    protected final static Logger logAbstract = Logger.getLogger(AbstractSquareDataLoadFile.class);
    private ArrayList numbersList = new ArrayList();
    protected String[][] values;

    public AbstractSquareDataLoadFile(final String finalPath, final String separator) {
        super(finalPath, separator);
        loadData();
    }

    @Override
    public void loadData() {
        handleInput();
        String[] subStr;
        int cols = 0;
        int rows = 0;
        try {
            final DataInputStream in = openInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                if (!strLine.equals("")) {
                    subStr = strLine.split(separator);
                    int newCols = subStr.length;
                    if (newCols > cols) {
                        cols = newCols;
                    }
                    for (int i = 0; i < subStr.length; i++) {
                        numbersList.add(subStr[i].trim());
                    }
                }
                if (!strLine.equals("")) {
                    rows++;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            logAbstract.debug("Error: " + e.getMessage());
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
    }

    @Override
    public void printData() {
        for (int i = 0; i < values.length; i++) {
            String[] strings = values[i];
            for (String string : strings) {
                logAbstract.debug(string);
            }
        }
    }

    @Override
    public String[][] getValues() {
        return values;
    }
}
