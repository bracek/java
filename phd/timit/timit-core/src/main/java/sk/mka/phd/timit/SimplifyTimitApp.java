package sk.mka.phd.timit;

import org.apache.log4j.Logger;
import sk.mka.phd.files.reader.IFileListing;
import sk.mka.phd.files.filters.FilteredFileListing;
import sk.mka.phd.timit.maven.TimitParams;

/**
 * 
 * @author bracek
 */
public class SimplifyTimitApp {

    final static Logger log = Logger.getLogger(SimplifyTimitApp.class);

    public static void main(String[] args) {
        final String inputFolder = TimitParams.getInputDirectory(TimitParams.TimitValues.dir);

        final IFileListing fileListing = new FilteredFileListing("_out.csv", "csv");
        final Simplify simplify = new Simplify(fileListing, inputFolder);
        simplify.parseFiles();


        System.out.println("... done ...");
    }
}

