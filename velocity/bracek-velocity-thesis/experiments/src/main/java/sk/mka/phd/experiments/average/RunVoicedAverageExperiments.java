package sk.mka.phd.experiments.average;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.Const.BBColums;
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

    /**
     *  combine starting Directory
     * @param args 
     */
    public static void main(String[] args) {
        try {
            String directorySuffix = null;
            directorySuffix = Mvn2tools.getPomValue(Mvn2tools.EXP_RESULTS_FOLDER_VOICED);
            final IGenerateExperimentsAverage generateExperimentsAverage = new GenerateAvarageVoicedExperimentsImpl(directorySuffix, BBColums.THREE);
            generateExperimentsAverage.init();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }
}
