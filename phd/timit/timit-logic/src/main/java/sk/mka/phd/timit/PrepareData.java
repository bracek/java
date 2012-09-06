package sk.mka.phd.timit;

import java.io.File;
import java.util.List;
import sk.mka.phd.files.filters.FilteredFileListing;
import sk.mka.phd.files.reader.IFileListing;
import sk.mka.phd.timit.maven.TimitParams;

/**
 *
 * @author bracek
 * @date Jul 20, 2010
 */
public class PrepareData {

    final String inputFolder = TimitParams.getInputDirectory(TimitParams.TimitValues.reductionInput);
    final IFileListing fileListing = new FilteredFileListing("_in.csv", "csv");

    void writeDataToFile() {

        final List<File> files = fileListing.getFiles(inputFolder);
        for (File file : files) {
            System.out.println("file = " + file);
        }
        System.out.println("done");
    }
}
