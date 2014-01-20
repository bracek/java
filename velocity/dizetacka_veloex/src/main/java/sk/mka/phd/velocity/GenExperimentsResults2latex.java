package sk.mka.phd.velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import sk.mka.phd.experiments.average.AbstractGenerateExperimentsAverage;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.velocity.data.LoadData;
import sk.mka.phd.velocity.data.impl.LoadInputDataImpl;

/**
 *
 * @author katrami
 * This class rewrite output from file defined as constant: INPUT_EXPERIMENTS_RESULTS to output file in latex format.
 * Input file has to be written in following format for each line:  1	80.71	97.08	64.32
 * Output file is defined by variable: Const.OUTPUT.OUTPUT_TABLE_TEX
 *
 */
public class GenExperimentsResults2latex extends AbstractGenerateExperimentsAverage {

    public static final String INPUT_EXPERIMENTS_RESULTS = "input/experiments/experiments_results.txt";
    public static final String INPUT_EXPERIMENTS_TEX_VM = "input/experiments/table_tex_experiments.vm";
    private static org.apache.log4j.Logger logger = Logger.getLogger(GenExperimentsResults2latex.class);

    public static void main(final String[] args) throws IOException {
        GenExperimentsResults2latex x = new GenExperimentsResults2latex();
        x.start();
    }

    private void start() throws RuntimeException, IOException {
        final LoadData loadInputData = new LoadInputDataImpl();
        final Object[][] info = loadInputData.loadDataAsObjects(INPUT_EXPERIMENTS_RESULTS);
        final VelocityEngine velocityEngine = new VelocityEngine();
        BufferedReader reader = null;
        try {
            reader = madeVelocityContext(info, velocityEngine, reader, INPUT_EXPERIMENTS_TEX_VM, Const.GENERAL_OUTPUT.OUTPUT_TABLE_TEX);
        } catch (final IOException e) {
            logger.debug(e.getMessage());
            throw new RuntimeException("Cannot generate output for template " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    @Override
    public void setUpFolderPath(final String lastFolder) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void extracPopClassBFromFolderName(final String folderName,
final  ArrayList expArrayList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
