package sk.mka.phd.files.reader;

import sk.mka.phd.files.filters.IFilterFileListing;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katrami
 */
public class FilterFileListing implements IFilterFileListing {

    private final FileFilter filter;

    public FilterFileListing(final FileFilter filter) {
        this.filter = filter;
    }

    @Override
    public List<File> getFiles(final String directory) {
        File folder = new File(directory);
        List<File> list = new ArrayList<File>();
        getFilesNames(folder, list);
        return list;
    }

    /***
     * Get recursively files by filter
     * @param folder
     * @param list
     */
    @Override
    public void getFilesNames(final File folder, final List<File> list) {
        folder.setReadOnly();
        File[] files = folder.listFiles(filter);
        for (int j = 0; j < files.length; j++) {
            list.add(files[j]);
            if (files[j].isDirectory()) {
                getFilesNames(files[j], list);
            }
        }
    }
}

