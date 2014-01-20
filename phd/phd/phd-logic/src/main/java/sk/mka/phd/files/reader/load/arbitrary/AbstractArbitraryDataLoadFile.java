package sk.mka.phd.files.reader.load.arbitrary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import sk.mka.phd.files.reader.load.AbstractDataLoadFile;
import sk.mka.phd.files.reader.load.LoadData;

/**
 *
 * @author bracek
 * @date Jun 29, 2010
 */
public abstract class AbstractArbitraryDataLoadFile extends AbstractDataLoadFile implements LoadData {

    protected ArrayList<Object> linesArrayList = new ArrayList<Object>();

    public AbstractArbitraryDataLoadFile(final String finalPath,
 final String separator) {
        super(finalPath, separator);
        loadData();
    }

    @Override
    public void loadData() {
        Object[] subStr;
        try {
            final DataInputStream in = openInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line

            while ((strLine = br.readLine()) != null) {
                //craete new ArrayList which will hold column data
                ArrayList<Object> columnArrayList = new ArrayList<Object>();
                // Print the content on the console
                log.debug(strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split(separator);
                    for (int i = 0; i < subStr.length; i++) {
                        columnArrayList.add(subStr[i]);
                    }
                    linesArrayList.add(columnArrayList);
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            //Catch exception if any
            log.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void printData() {
        for (Object object : linesArrayList) {
            ArrayList<Object> col = (ArrayList<Object>) object;
            for (Object object1 : col) {
                System.out.print(object1 + "\t");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Object> getLines() {
        return linesArrayList;
    }
}
