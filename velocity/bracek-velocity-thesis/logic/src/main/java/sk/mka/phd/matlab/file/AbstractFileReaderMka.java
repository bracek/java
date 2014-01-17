package sk.mka.phd.matlab.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author bracek
 * @date Nov 16, 2009
 */
public class AbstractFileReaderMka implements FileReaderMka {

    private static Logger log = Logger.getLogger(AbstractFileReaderMka.class);

    /**
     * Recursively walk a directory tree and return a List of all
     * Files found; the List is sorted using File.compareTo().
     *
     * @param aStartingDir is a valid directory, which can be read.
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public List<File> getFileListing(final File aStartingDir) throws FileNotFoundException {
        validateDirectory(aStartingDir);
        List<File> result = getFileListingNoSort(aStartingDir);
        Collections.sort(result);
        return result;
    }

    @Override
    public List<File> getFileListingNoSort(final File aStartingDir) throws FileNotFoundException {
        List<File> result = new ArrayList<File>();
        File[] filesAndDirs = aStartingDir.listFiles();
        List<File> filesDirs = Arrays.asList(filesAndDirs);

        for (File file : filesDirs) {
            result.add(file);
            if (!file.isFile()) {
                //must be a directory
                //recursive call!
                ArrayList files = (ArrayList) getFileListingNoSort(file);
                result.addAll(files);
            }
        }
        return result;
    }

    /**
     * Directory is valid if it exists, does not represent a file, and can be read.
     * @throws FileNotFoundException
     */
    @Override
    public void validateDirectory(final File aDirectory) throws FileNotFoundException {
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
    protected String filename;

    @Override
    public Writer openFileForWriting(final String fileName) {
        this.filename = fileName;
        Writer output = null;
        try {
            File file = new File(fileName);
            output = new BufferedWriter(new FileWriter(file));
            return output;
        } catch (IOException ex) {
            log.fatal(ex);
        }
        return output;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }
}
