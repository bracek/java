package sk.mka.phd.files.reader;

import java.io.File;
import java.util.List;

/**
 *
 * @author bracek
 */
public interface IFileListing {

    /***
     * Get recursively files from directory
     * @param directory
     * @return
     */
     List<File> getFiles(final String directory);
}
