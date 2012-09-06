package sk.mka.phd.timit;

import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.files.reader.IFileListing;
import sk.mka.phd.files.filters.FilteredFileListing;
import sk.mka.phd.timit.maven.TimitParams;

/**
 * 
 * @author bracek
 */
public class PrepareDataForNNApp {

    final static Logger log = Logger.getLogger(PrepareDataForNNApp.class);

    public static void main(String[] args) {

        PrepareData prepareData = new PrepareData();
        prepareData.writeDataToFile();

    }
}


