package sk.mka.phd.files.filters;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author bracek
 */
public class FileExtensionFilter implements FileFilter {

    private final String maska;

    public FileExtensionFilter(final String maska) {
        this.maska = maska;
    }

    @Override
    public boolean accept(final File pathname) {
        boolean bReturn = false;
        if (pathname.isDirectory()) {
            bReturn = true;
        } else {
            final String absolutePath = pathname.getAbsolutePath().toString();
            if (absolutePath.endsWith((maska))) {
                bReturn = true;
            }
        }
        return bReturn;
    }
}
