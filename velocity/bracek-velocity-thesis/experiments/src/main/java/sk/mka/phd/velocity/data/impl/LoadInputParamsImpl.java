package sk.mka.phd.velocity.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.velocity.data.file.AbstractFileLoadData;
import sk.mka.phd.velocity.data.file.IFileLoadData;

/**
 *
 * @author bracek
 * @date Nov 29, 2009
 */
public class LoadInputParamsImpl extends AbstractFileLoadData implements IFileLoadData {

    private static Logger log = Logger.getLogger(LoadInputParamsImpl.class);
    protected final ArrayList nnParams = new ArrayList();

    /**
     * read only single line from file
     * @param filename
     * @return
     */
    @Override
    public String[][] loadData(final String filename) {

        String[][] values = null;
        String[][] numbers = null;
        try {
            numbers = getFileContent(filename);
//            numbers = super.loadData(filename);
            for (int i = 0; i < numbers.length; i++) {
                String[] strings = numbers[i];
                boolean bFounded = false;
                for (int j = 0; j < strings.length; j++) {
                    String string = strings[j];
                    for (Const.InputParams param : Const.InputParams.values()) {
                        if (param.toString().toLowerCase().equals(string)) {
                            bFounded = true;
                            continue;
                        }
                    }

                    if (bFounded && j == 1) {
                        nnParams.add(string);
                    }
                }
            }

        } catch (IOException ex) {
            log.debug(ex);
        }

        values = new String[1][nnParams.size()];
        int row = 0;
        int col = 0;
        Object x;
        for (int i = 0; i < nnParams.size(); i++) {
            x = nnParams.get(i);
            values[row][col] = (String) x;
            col++;
        }
        return values;
    }
}
