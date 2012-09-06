package sk.mka.phd.velocity.data.impl;

import sk.mka.phd.velocity.data.AbstractLoadData;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.velocity.data.LoadData;

/**
 *
 * @author katrami
 */
public class LoadVoicedVoicelessDataImpl extends AbstractLoadData implements LoadData {
    public static final String VOICELESS = "neznela";
    public static final String VOICED = "znela";

    private final List<Integer> nnId = new ArrayList<Integer>();
    private final ArrayList voicedUnvoicedList = new ArrayList();
    private static Logger log = Logger.getLogger(LoadVoicedVoicelessDataImpl.class);

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
                System.out.println(strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split("\t");
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        if (i == 1) {
                            if (Integer.valueOf(subStr[0]) == 27) {
                                if (subStr[1].contains("h#")) {
                                    subStr[1] = subStr[1].replace(subStr[1], "h\\#");
                                }
                            }
                        } else if (i == 2) {
                            if (Integer.valueOf(subStr[i]) == 0) {
                                subStr[i] = subStr[i].replace(subStr[i],VOICELESS);
                            } else {
                                subStr[i] = subStr[i].replace(subStr[i],VOICED);
                            }

                        }
                        voicedUnvoicedList.add(subStr[i]);
                        if (i == 0) { //getting neuron id
                            int index = Integer.valueOf(subStr[i]);
                            nnId.add(index);
                        }
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
            log.debug(e.getMessage());
        }
        String[][] numbers = new String[getRows()][getCols()];
        int row = 0;
        int col = 0;
        Object x;
        for (int i = 0; i < voicedUnvoicedList.size(); i++) {
            x = voicedUnvoicedList.get(i);
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
}
