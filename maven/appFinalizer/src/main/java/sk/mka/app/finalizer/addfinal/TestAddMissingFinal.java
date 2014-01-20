package sk.mka.app.finalizer.addfinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import sk.mka.app.finalizer.IAction;

/**
 * Recursive file listing under a specified directory.
 * 
 * @author javapractices.com
 * @author Miroslav Katrak
 */
public final class TestAddMissingFinal {

	/**
	 * Demonstrate use.
	 * 
	 * @param aArgs
	 *            - <tt>aArgs[0]</tt> is the full name of an existing directory
	 *            that can be read.
	 */
	public static void main(final String... aArgs) throws FileNotFoundException {		final File startingDirectory = new File(aArgs[0]);

		final IAction addMissingFinal = new AddMissingFinalImpl();

		final List<File> files = addMissingFinal
				.getFileListing(startingDirectory);

		for (File file : files) {
			if (file.isFile()) {
				addMissingFinal.parseFile(file);
			}
		}
	}

}
