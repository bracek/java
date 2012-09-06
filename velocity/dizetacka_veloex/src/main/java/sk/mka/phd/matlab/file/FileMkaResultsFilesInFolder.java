package sk.mka.phd.matlab.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bracek
 * @date Nov 16, 2009
 */
public class FileMkaResultsFilesInFolder extends AbstractFileMka implements FileMka {

    private static final String SVN_DOT_EXCLUDE = ".svn";

    @Override
    public List<File> getFileListingNoSort(File aStartingDir) throws FileNotFoundException {
        List<File> result = new ArrayList<File>();
        File[] filesAndDirs = aStartingDir.listFiles();
        List<File> filesDirs = Arrays.asList(filesAndDirs);
        for (File file : filesDirs) {
            if (!file.isFile()) {
                if (!file.getPath().contains(SVN_DOT_EXCLUDE)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
