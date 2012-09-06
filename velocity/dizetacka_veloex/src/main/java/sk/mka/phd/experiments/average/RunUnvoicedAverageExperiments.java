package sk.mka.phd.experiments.average;

import java.io.FileNotFoundException;
import java.io.IOException;
import sk.mka.phd.tools.maven2.Mvn2tools;

/**
 *
 * @author bracek
 * @date Nov 17, 2009
 */
public class RunUnvoicedAverageExperiments {

    /**
     * set folder, which contains subfolder for individual experiment. Each experiment has to contain result.txt file 
     * @param args 
     */
//    private static final String directorySuffix = "uA1B0";
//    private static final String directorySuffix = "ua0b1";
//    private static final String directorySuffix = "ua0b1";
    public static void main(String[] args) {
        try {
            String directorySuffix = null;
//        noDuplication-a0b0
            directorySuffix = Mvn2tools.getPomValue(Mvn2tools.EXP_RESULTS_FOLDER_UNVOICED);
            final GenerateExperimentsAverageInterface generateExperimentsAverage = new GenerateAvarageUnvoicedExperimentsImpl(directorySuffix);
            generateExperimentsAverage.init();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



    }
}
