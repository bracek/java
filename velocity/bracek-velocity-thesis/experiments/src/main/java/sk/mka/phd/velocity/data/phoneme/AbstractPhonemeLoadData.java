package sk.mka.phd.velocity.data.phoneme;

import sk.mka.phd.data.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.SeparatorConst;

/**
 *
 * @author katrami
 */
public abstract class AbstractPhonemeLoadData extends AbstractLoadData implements PhonemeLoadData {

    protected final List<String> phonemaArrayList = new ArrayList<String>();
    protected final List<String> numbersList = new ArrayList<String>();
    private static Logger log = Logger.getLogger(AbstractPhonemeLoadData.class);

    @Override
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
}
