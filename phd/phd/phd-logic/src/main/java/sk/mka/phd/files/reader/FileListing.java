package sk.mka.phd.files.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Recursive file listing under a specified directory.
 *
 * @author javapractices.com
 * @author Alex Wong
 * @author anonymous user
 */
public abstract class FileListing implements IFileListing {

    protected List<File> files = null;

    @Override
    public abstract List<File> getFiles(String directory);

    /**
     * Recursively walk a directory tree and return a List of all
     * Files found; the List is sorted using File.compareTo().
     *
     * @param aStartingDir is a valid directory, which can be read.
     */
    protected List<File> getFileListing(
            File aStartingDir) throws FileNotFoundException {
        validateDirectory(aStartingDir);
        List<File> result = getFileListingNoSort(aStartingDir);
        Collections.sort(result);
        return result;
    }

    private List<File> getFileListingNoSort(
            File aStartingDir) throws FileNotFoundException {
        List<File> result = new ArrayList<File>();
        File[] filesAndDirs = aStartingDir.listFiles();
        List<File> filesDirs = Arrays.asList(filesAndDirs);
        for (File file : filesDirs) {
            result.add(file); //always add, even if directory
            if (!file.isFile()) {
                //must be a directory
                //recursive call!
                List<File> deeperList = getFileListingNoSort(file);
                result.addAll(deeperList);
            }
        }
        return result;
    }

    /**
     * Directory is valid if it exists, does not represent a file, and can be read.
     */
    private void validateDirectory(
            File aDirectory) throws FileNotFoundException {
        if (aDirectory == null) {
            throw new IllegalArgumentException("Directory should not be null.");
        }
        if (!aDirectory.exists()) {
            throw new FileNotFoundException("Directory does not exist: " + aDirectory);
        }
        if (!aDirectory.isDirectory()) {
            throw new IllegalArgumentException("Is not a directory: " + aDirectory);
        }
        if (!aDirectory.canRead()) {
            throw new IllegalArgumentException("Directory cannot be read: " + aDirectory);
        }
    }
}

