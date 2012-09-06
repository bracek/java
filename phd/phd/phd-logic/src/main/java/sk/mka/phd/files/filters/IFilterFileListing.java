/*
 */
package sk.mka.phd.files.filters;

import java.io.File;
import java.util.List;
import sk.mka.phd.files.reader.IFileListing;

/**
 *
 * @author katrami
 */
public interface IFilterFileListing extends IFileListing {

    /**
     * Get recursively files by filter
     * @param folder
     * @param list
     */
    void getFilesNames(File folder, List<File> list);
}
