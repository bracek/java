package sk.mka.phd.velocity.data.impl;

import java.io.IOException;
import org.apache.log4j.Logger;
import sk.mka.phd.data.AbstractLoadData;
import sk.mka.phd.velocity.data.file.IFileLoadData;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public class LoadFirstLineImpl extends AbstractLoadData implements IFileLoadData {

    private static Logger log = Logger.getLogger(LoadFirstLineImpl.class);

    @Override
    public Object[][] loadDataAsObjects(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[][] getFileContent(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    /**
//     * read only single line from file
//     * @param filename
//     * @return
//     */
//    @Override
//    public String[][] loadData(String filename) {
//        String[][] numbers = null;
//        try {
//            numbers = super.loadData(filename);
//        } catch (IOException ex) {
//            log.fatal(ex.getMessage());
//        }
//
//        String[][] data = new String[1][getCols()];
//        for (int i = 0; i < getCols(); i++) {
//            String[] strings = numbers[0];
//            for (int j = 0; j < strings.length; j++) {
//                String colVector = strings[j];
//                data[0][j] = colVector;
//            }
//
//        }
//        return data;
//    }
}
