package sk.mka.phd.timit.maven;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import sk.mka.phd.constants.PhdConstants;
import sk.mka.phd.maven.MavenParams;

/**
 *
 * @author bracek
 * @date Jul 7, 2010
 */
public class TimitParams {

    protected static Logger log = Logger.getLogger(TimitParams.class);

    public enum TimitValues {

        phonemeReduction,
        phonemeDistribution,
        groupsOutputRepresentation,
        groupsOutputXml,
        dir,
        reductionInput
    }

    public static final String getInputDirectory(final TimitValues dir) {

        String userDir = null;
        final String absPath = System.getProperty(PhdConstants.USER_DIR);

        try {
            userDir = MavenParams.getPomValue(dir.toString());
        } catch (FileNotFoundException ex) {
            log.fatal(ex);
        } catch (IOException ex) {
            log.fatal(ex);
        }
        final String finalPath = absPath + "/" + userDir;
        return finalPath;
    }
}
