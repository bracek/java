package sk.mka.phd.matlab.generator.all;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.file.FileMkaImpl;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public class HelperGenerateIterator {

    static final String PHONEME_FILENAME = "phoneme.m";
    static final String CONFIG_FILENAME = "target/classes/config-logic.properties";
    private int start = 0;
    private static String[] divisionArray;
    private static Logger log = Logger.getLogger(HelperGenerateIterator.class);

    public void main() {
        int i = 0;
        Writer output = null;
        try {
            try {
                FileInputStream fstream = new FileInputStream(CONFIG_FILENAME);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    System.out.println(strLine);
                    divisionArray = strLine.split("=");
                    String key = divisionArray[0];
                    if (key.trim().equals("start.id")) {
                        log.debug(key);
                        String value = divisionArray[1];
                        String cleanValue = value.trim();
                        this.start = (int) Integer.valueOf(cleanValue);
                        log.debug(this.start);
                    }
                    i++;
                }
                in.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            log.debug("i:" + i);

            output = new FileMkaImpl().openFileForWriting(PHONEME_FILENAME);
            int j = 1;
            for (int idx = start; idx < Const.NUMBER_OF_PHONEME; idx++) {
                if (idx != 20 && idx != 27) { //only for pattern dr8, beccause 20-th phonme is missing
                    final String x = "nnIdsForFutherTraining(" + j + ")=" + idx + ";";
                    output.write(x + "\n");
                    System.out.println(x);
                    j++;
                }
            }
            System.out.println("Your file: " + PHONEME_FILENAME + " has been written successfuly");
            output.close();
        } catch (IOException ex) {
            log.debug(ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                log.debug(ex);
            }
        }

    }
}
