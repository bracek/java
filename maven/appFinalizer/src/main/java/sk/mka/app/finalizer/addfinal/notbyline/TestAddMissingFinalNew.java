package sk.mka.app.finalizer.addfinal.notbyline;

import sk.mka.app.finalizer.IAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Recursive file listing under a specified directory.
 *
 * @author javapractices.com
 * @author Miroslav Katrak
 */
public final class TestAddMissingFinalNew {

    /**
     * Demonstrate use.
     *
     * @param aArgs - <tt>aArgs[0]</tt> is the full name of an existing directory
     *              that can be read.
     */
    public static void main(final String... aArgs) throws FileNotFoundException {
        final File startingDirectory = new File(aArgs[0]);

        final IAction addMissingFinal = new AddMissingFinalNew();

        final List<File> files = addMissingFinal
                .getFileListing(startingDirectory);

        int processedFilesCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("file: " + file.getName());
                addMissingFinal.parseFile(file);
                processedFilesCount++;
            }
        }

        System.out.println("number of process files: " + processedFilesCount);
        System.out.println(addMissingFinal.getNumberOfAddedFinalKeyword() + " final keyword was added during processing");
    }

}
