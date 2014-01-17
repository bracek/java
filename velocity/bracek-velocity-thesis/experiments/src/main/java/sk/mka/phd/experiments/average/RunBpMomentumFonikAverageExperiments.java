package sk.mka.phd.experiments.average;

import java.io.FileNotFoundException;
import java.io.IOException;
import sk.mka.phd.matlab.cons.Const.BBColums;
import sk.mka.phd.tools.maven2.Mvn2tools;

/**
 *
 * @author bracek
 * @date Dec 10, 2009
 */
public class RunBpMomentumFonikAverageExperiments {

    public static void main(final String[] args) {
        try {
            String directorySuffix = null;
            directorySuffix = Mvn2tools.getPomValue(Mvn2tools.EXP_BPMOMENTUM_FONIK);
            final IGenerateExperimentsAverage generateExperimentsAverage = new GenerateAverageBpMomentumExperimentsImpl(directorySuffix, BBColums.FIVE);
            generateExperimentsAverage.init();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunVoicedAverageExperiments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



    }
}
