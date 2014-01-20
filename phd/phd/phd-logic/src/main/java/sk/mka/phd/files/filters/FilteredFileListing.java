package sk.mka.phd.files.filters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.files.reader.FileListing;

/**
 *
 * @author bracek
 */
public class FilteredFileListing extends FileListing {

    private final static Logger log = Logger.getLogger(FilteredFileListing.class);
    private final String mask;
    private final String extension;

    public FilteredFileListing(final String mask,
 final String extension) {
        this.mask = mask;
        this.extension = extension;
    }

    @Override
    public List<File> getFiles(final String directory) {
        try {
            files = super.getFileListing(new File(directory));
        } catch (FileNotFoundException ex) {
            log.fatal(ex.getMessage());
        }

        final List<File> filteredFiles = new ArrayList<File>();
        for (File file : files) {
            if (file.getAbsolutePath().endsWith('.' + extension)) {
                if (file.getPath().contains(mask)) {
                    filteredFiles.add(file);
                }
            }
        }
        return filteredFiles;
    }
}
