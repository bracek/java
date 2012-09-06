package sk.mka.phd.velocity.data.impl;

import java.io.IOException;
import sk.mka.phd.velocity.data.*;
import org.apache.log4j.Logger;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public class LoadDataImpl extends AbstractLoadData implements LoadData {

    private static Logger log = Logger.getLogger(LoadDataImpl.class);

//TODO rewrite in way that you will call parent and only first line from parent
    /**
     * read only single line from file
     * @param filename
     * @return
     */
    @Override
    public String[][] loadData(String filename) {
        String[][] numbers = null;
        try {
            numbers = super.loadData(filename);
        } catch (IOException ex) {
            log.fatal(ex.getMessage());
        }

        String[][] data = new String[1][getCols()];
        for (int i = 0; i < getCols(); i++) {
            String[] strings = numbers[0];
            for (int j = 0; j < strings.length; j++) {
                String colVector = strings[j];
                data[0][j] = colVector;
            }

        }
        return data;

    }
}
