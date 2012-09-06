package sk.mka.phd.velocity;

import sk.mka.phd.velocity.data.PhonemeLoadData;
import sk.mka.phd.velocity.data.impl.LoadDataImpl;
import sk.mka.phd.velocity.data.impl.LoadInputDataImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.velocity.data.LoadData;

public class GenerateResults2latex {

    private static org.apache.log4j.Logger log = Logger.getLogger(GenerateResults2latex.class);

    public static void main(String[] args) throws IOException {

        final LoadData loadInputData = new LoadInputDataImpl();
        final String numbers[][] = loadInputData.loadData(Const.INPUT.RESULTS);
        final List<String> ava = ((PhonemeLoadData) loadInputData).rateAvarage();

        waitUntilGnuplotGraphWillBeReady();

        final List<String> phone = ((PhonemeLoadData) loadInputData).loadPhonemaList(Const.INPUT.PHONELIST);

        final LoadData loadMfc = new LoadDataImpl();
        final String inputParams[][] = loadMfc.loadData(Const.INPUT.MFC_PARAMS);

        //neural network topology
        final LoadData networkTopology = new LoadDataImpl();
        final String info[][] = networkTopology.loadData(Const.INPUT.NEURAL_NETWORK_TOPOLOGY);

        //train, test patern, number of epochs
        final LoadData patternData = new LoadDataImpl();
        final String pattern[][] = patternData.loadData(Const.INPUT.PATTERN_EPOCHS);


        final List<Integer> ids = ((PhonemeLoadData) loadInputData).getNnId();
        final VelocityEngine velocityEngine = new VelocityEngine();

        BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File(Const.INPUT.VM.TABLE_TEX_VM)));
            final FileWriter fileWriter = new FileWriter(new File(Const.GENERAL_OUTPUT.OUTPUT_TABLE_TEX));

            // Output
            final Writer writer = new BufferedWriter(fileWriter);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("ava", ava);
            model.put("numbers", numbers);
            model.put("info", info);
            model.put("pattern", pattern);
            model.put("inputParams", inputParams);
            model.put("phone", phone);
            model.put("ids", ids);

            // Velocity context
            final VelocityContext context = new VelocityContext(model);
            // Generating file according template
            velocityEngine.evaluate(context, writer, "", reader);
            writer.close();
        } catch (final IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException("Cannot generate output for template " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void waitUntilGnuplotGraphWillBeReady() throws IOException {
        Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_GRAPH_GENERAL_GP); //generate graph which will be included to table.tex later
        Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_GRAPH_GENERAL_EPS); //generate graph which will be included to table.tex later
        boolean exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.OUTPUT_GRAPH)).exists();
        } while (exists == false);

        Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_GRAPH_A_GP);
        Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_GRAPH_A_EPS);
        exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.OUTPUT_GRAPH_CLASS_A)).exists();
        } while (exists == false);

        Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_GRAPH_B_GP);
        Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_GRAPH_B_EPS);
        exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.OUTPUT_GRAPH_CLASS_B)).exists();
        } while (exists == false);

        Runtime.getRuntime().exec(Const.GNUPLOT.APATTERN_SUCCESS_GP);
        Runtime.getRuntime().exec(Const.EPSTOPDF.APATTERN_SUCCESS_EPS);
        exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.APATTERN_SUCCESS)).exists();
        } while (exists == false);


        Runtime.getRuntime().exec(Const.GNUPLOT.BPATTERN_SUCCESS_GP);
        Runtime.getRuntime().exec(Const.EPSTOPDF.BPATTERN_SUCCESS_EPS);
        exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.BPATTERN_SUCCESS)).exists();
        } while (exists == false);


        Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_REDUCTIONB);
        Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_REDUCTIONB);
        exists = false;
        do {
            exists = (new File(Const.GENERAL_OUTPUT.OUTPUT_REDUCTIONB)).exists();
        } while (exists == false);
    }
}
