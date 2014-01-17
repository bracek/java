package sk.mka.phd.experiments.average;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import sk.mka.phd.tools.maven2.Mvn2tools;

/**
 *
 * @author bracek
 * @date Nov 17, 2009
 * Class generate table.tex, which contains list of voiced experiments based on population class B. There is neccessary to sort input folder.
 * Class also generate file output/experiment.dat with this value. Than you can call make in folder output to plot experiments results.
 */
public class RunVoicedAverageExperiments {

    public Logger log = Logger.getLogger(RunVoicedAverageExperiments.class);

//    static final String suffix = "A0-B1";
//    static final String suffix = "a1b0";
//    static final String suffix = "multiDuplicationA1B1";
//    private static final String directorySuffix = "noDuplication-a0b0";
//    private static final String directorySuffix = "noDuplication-a0b0";
    /**
     *  combine starting Directory
     * @param args 
     */
//    private static final String directoryPath = "/home/" + user + "/subversion/kemt/katrak/matlab/results/expertSystems/voiced/" + suffix + "/";
    public static void main(final String[] args) {
        try {
            String directorySuffix = null;
//        noDuplication-a0b0
            directorySuffix = Mvn2tools.getPomValue(Mvn2tools.EXP_RESULTS_FOLDER_VOICED);
            final GenerateExperimentsAverageInterface generateExperimentsAverage = new GenerateAvarageVoicedExperimentsImpl(directorySuffix);
            generateExperimentsAverage.init();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }
}
