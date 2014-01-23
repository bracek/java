package sk.mka.app.finalizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractAction implements IAction {

    protected int numberOfAddedFinalKeyword = 0;


    /**
     * Directory is valid if it exists, does not represent a file, and can be
     * read.
     */
    public final void validateDirectory(final File aDirectory) throws FileNotFoundException {
        if (aDirectory == null) {
            throw new IllegalArgumentException("Directory should not be null.");
        }
        if (!aDirectory.exists()) {
            throw new FileNotFoundException("Directory does not exist: "
                    + aDirectory);
        }
        if (!aDirectory.isDirectory()) {
            throw new IllegalArgumentException("Is not a directory: "
                    + aDirectory);
        }
        if (!aDirectory.canRead()) {
            throw new IllegalArgumentException("Directory cannot be read: "
                    + aDirectory);
        }
    }

    /**
     * Recursively walk a directory tree and return a List of all Files found;
     * the List is sorted using File.compareTo().
     *
     * @param aStartingDir is a valid directory, which can be read.
     */
    public final List<File> getFileListing(final File aStartingDir) throws FileNotFoundException {
        validateDirectory(aStartingDir);
        getFileListingNoSort(aStartingDir);
        return getFileListingNoSort(aStartingDir);

    }


    private List<File> getFileListingNoSort(final File aStartingDir) throws FileNotFoundException {
        final List<File> result = new ArrayList<File>();
        final File[] filesAndDirs = aStartingDir.listFiles();
        if (filesAndDirs != null) {
            final List<File> filesDirs = Arrays.asList(filesAndDirs);
            for (File file : filesDirs) {
                result.add(file); // always add, even if directory
                if (!file.isFile()) {
                    final List<File> deeperList = getFileListingNoSort(file);
                    result.addAll(deeperList);
                }
            }
        }
        return result;
    }

    @Override
    public final int getNumberOfAddedFinalKeyword() {
        return numberOfAddedFinalKeyword;
    }
}
