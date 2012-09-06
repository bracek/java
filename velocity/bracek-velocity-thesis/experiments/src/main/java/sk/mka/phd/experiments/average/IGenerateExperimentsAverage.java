package sk.mka.phd.experiments.average;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bracek
 * @date Nov 17, 2009
 */
public interface IGenerateExperimentsAverage {

    String HOME = "/home/";
        String CHARACTER_ = "_";
    String EXPERIMENTS_PATH_RELATIVE = "/svn/kemt/katrak/matlab/results/expertSystems/";
    String STATISTICS_BPMOMENTUM_FONIK = "/svn/kemt/katrak/phd/workspace/c++/bpMomentumFonik/results/statistics/";
    String OUTPUT_AVERAGE_EXPERIMENTS = "graphAverageExperimentsPopClassB.dat";
    String RESULT_TXT = "result.txt";

    public void setUpFolderPath(final String lastFolder);

    /**
     * Method iw
     * @param folderName 
     * @param info
     * @return 
     */
    ArrayList countAvarageForFile(String folderName, Object[][] info);

    /**
     *
     * @param experimentsList
     * @return
     */
    Object[][] createFileObject(ArrayList experimentsList);

    /**
     *
     * @param folderName
     * @param expArrayList
     */
    void extracPopClassBFromFolderName(final String folderName, ArrayList expArrayList);

    /**
     * start business logic for class
     * @throws IOException
     */
    public void init() throws IOException;

    /**
     *
     * @param file
     * @return 
     */
    ArrayList readFile(final File file);

    String getExperimentStartFolder();

    void setExperimentStartFolder(String experimentStartFolder);
}
