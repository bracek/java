package sk.mka.phd.experiments.average;

import java.io.FileNotFoundException;
import java.io.IOException;
import sk.mka.phd.matlab.cons.Const.BBColums;
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
    public static void main(String[] args) {
        try {
            String directorySuffix = null;
            directorySuffix = Mvn2tools.getPomValue(Mvn2tools.EXP_RESULTS_FOLDER_UNVOICED);
            final IGenerateExperimentsAverage generateExperimentsAverage = new GenerateAvarageUnvoicedExperimentsImpl(directorySuffix, BBColums.THREE);
            generateExperimentsAverage.init();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



    }
}
