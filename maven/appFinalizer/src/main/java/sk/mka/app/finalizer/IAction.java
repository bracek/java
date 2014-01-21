package sk.mka.app.finalizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface IAction {

	List<File> getFileListing(File startingDirectory)
			throws FileNotFoundException;

	void parseFile(File file);

    int getNumberOfAddedFinalKeyword();
}
