
package sk.mka.phd.matlab.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.List;

/**
 *
 * @author bracek
 * @date Nov 16, 2009
 */
public interface FileMka {

    /**
     * Recursively walk a directory tree and return a List of all
     * Files found; the List is sorted using File.compareTo().
     *
     * @param aStartingDir is a valid directory, which can be read.
     * @return
     * @throws FileNotFoundException
     */
    List<File> getFileListing(File aStartingDir) throws FileNotFoundException;

   
    List<File> getFileListingNoSort(File aStartingDir) throws FileNotFoundException;

    /**
     * Directory is valid if it exists, does not represent a file, and can be read.
     * @param aDirectory
     * @throws FileNotFoundException
     */
    void validateDirectory(File aDirectory) throws FileNotFoundException;


    /**
     * Open file for writing and return buffered writer
     * @param fileName
     * @return
     */
    public Writer openFileForWriting(final String fileName);


}
