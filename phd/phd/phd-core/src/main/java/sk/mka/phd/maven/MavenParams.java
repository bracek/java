/*
 */
package sk.mka.phd.maven;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import sk.mka.phd.constants.PhdConstants;

/**
 *
 * @author katrami
 * @date Jul 3, 2010
 */
public class MavenParams {

    private static String[] divisionArray;
    protected static Logger log = Logger.getLogger(MavenParams.class);
    private static boolean writeMavenParams = true;

    public static final String getPomValue(            final String inKey)
            throws FileNotFoundException, IOException {
        int i = 0;
        String cleanValue = null;
        final FileInputStream fstream = new FileInputStream(PhdConstants.CONFIG_FILENAME);
        final DataInputStream in = new DataInputStream(fstream);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;

        while ((strLine = br.readLine()) != null) {
            //System.out.println(strLine);
            if (writeMavenParams) {
                log.info(strLine);
            }
            divisionArray = strLine.split("=");
            final String key = divisionArray[0];
            if (key.trim().equals(inKey.toString())) {
                String value = divisionArray[1];
                cleanValue =
                        value.trim();
            }
            i++;
        }

        in.close();
        writeMavenParams = false;
        return cleanValue;
    }

    public static String getInputDirectory(final String dir) {
        String userDir = null;
        final String absPath = System.getProperty(PhdConstants.USER_DIR);

        try {
            userDir = MavenParams.getPomValue(dir);
        } catch (FileNotFoundException ex) {
            log.fatal(ex);
        } catch (IOException ex) {
            log.fatal(ex);
        }
        final String finalPath = absPath + "/" + userDir;
        return finalPath;
    }
}
