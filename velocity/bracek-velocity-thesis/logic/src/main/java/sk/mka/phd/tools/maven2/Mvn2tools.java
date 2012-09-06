package sk.mka.phd.tools.maven2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author bracek
 * @date Nov 16, 2009
 */
public class Mvn2tools {

    //m2 keys
    public static final String EXP_RESULTS_FOLDER_VOICED = "exp.results.folder.voiced";
    public static final String EXP_RESULTS_FOLDER_UNVOICED = "exp.results.folder.unvoiced";
    public static final String EXP_BPMOMENTUM_FONIK = "exp.bpMomentumfonik";
    static final String CONFIG_FILENAME = "target/classes/config-logic.properties";
    private static String[] divisionArray;

    public static String getPomValue(
            final String inKey)
            throws FileNotFoundException, IOException {
        int i = 0;
        String cleanValue = null;
        FileInputStream fstream = new FileInputStream(CONFIG_FILENAME);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;

        while ((strLine = br.readLine()) != null) {
            System.out.println(strLine);
            divisionArray =
                    strLine.split("=");
            String key = divisionArray[0];
            if (key.trim().equals(inKey)) {
                String value = divisionArray[1];
                cleanValue =
                        value.trim();
            }

            i++;
        }

        in.close();
        return cleanValue;
    }
}
