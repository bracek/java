package sk.mka.app.finalizer.unusedmodifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import sk.mka.app.finalizer.IAction;

/**
 * Recursive file listing under a specified directory.
 * 
 * @author Miroslav Katrak
 * @author anonymous user
 */
public final class TestRemoveUnusedModifier {

	/**
	 * Demonstrate use.
	 * 
	 * @param aArgs
	 *            - <tt>aArgs[0]</tt> is the full name of an existing directory
	 *            that can be read.
	 */
	public static void main(final String... aArgs) throws FileNotFoundException {
		final File startingDirectory = new File(aArgs[0]);

		final IAction removeUnusedModifier = new RemoveUnusedModifierImpl();

        final List<File> files = removeUnusedModifier
                .getFileListing(startingDirectory);

        // print out all file names, in the the order of File.compareTo()
		for (File file : files) {
			if (file.isFile()) {
				removeUnusedModifier.parseFile(file);
			}
		}
	}

}
