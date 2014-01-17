package sk.mka.phd.velocity;

import java.util.logging.Level;
import sk.mka.phd.velocity.threads.GenerateGraphThread;
import sk.mka.phd.velocity.threads.GraphAThread;
import sk.mka.phd.velocity.data.phoneme.PhonemeLoadData;
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
import sk.mka.phd.data.ILoadData;
import sk.mka.phd.velocity.data.impl.LoadInputParamsImpl;
import sk.mka.phd.velocity.data.phoneme.PhonemeLoadDataImpl;
import sk.mka.phd.velocity.data.impl.LoadTopologyImpl;
import sk.mka.phd.velocity.threads.GraphABThread;
import sk.mka.phd.velocity.threads.GraphAPatternSuccessThread;
import sk.mka.phd.velocity.threads.GraphBAThread;
import sk.mka.phd.velocity.threads.GraphBPatternSuccessThread;
import sk.mka.phd.velocity.threads.GraphBThread;
import sk.mka.phd.velocity.threads.GraphReductionBThread;

public class GenerateResults2latex {

    private static org.apache.log4j.Logger log = Logger.getLogger(GenerateResults2latex.class);
    private static final String CONFIG_FILE = "input/config";

    public static void main(final String[] args) throws IOException {

        final ILoadData loadPhonemeData = new PhonemeLoadDataImpl();
        final String numbers[][] = loadPhonemeData.loadData(Const.INPUT.RESULTS);
        final List<String> ava = ((PhonemeLoadData) loadPhonemeData).rateAvarage();
        waitUntilGnuplotGraphWillBeReady();

        final List<String> phone = ((PhonemeLoadData) loadPhonemeData).loadPhonemaList(Const.INPUT.PHONELIST);

        //load topology from config file number of neurons for each layer
        final ILoadData topologyLoad = new LoadTopologyImpl();
        topologyLoad.setSeparator("=");
        final String info[][] = topologyLoad.loadData(CONFIG_FILE);

        //input params (alfa, gama, momentum)
        final ILoadData loadInputParams = new LoadInputParamsImpl();
        loadInputParams.setSeparator("=");
        final String inputParams[][] = loadInputParams.loadData(CONFIG_FILE);
        final List<Integer> ids = ((PhonemeLoadData) loadPhonemeData).getListOfPhonemeId();
        final VelocityEngine velocityEngine = new VelocityEngine();

        BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File(Const.INPUT.VM.TABLE_TEX_VM)));
            final FileWriter fileWriter = new FileWriter(new File(Const.OUTPUT.TXT.OUTPUT_TABLE_TEX));

            // Output
            final Writer writer = new BufferedWriter(fileWriter);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("ava", ava);
            model.put("numbers", numbers);
            model.put("info", info);
//            model.put("pattern", pattern);
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
        try {

            long beggining = System.currentTimeMillis();
            final int threadTimeWaiting = 1000;

            //generalGraph
            GenerateGraphThread generalGraph = new GenerateGraphThread("generalGraph");
            setThreadLogic(generalGraph, threadTimeWaiting);

            //graphA
            GraphAThread graphA = new GraphAThread("graphA");
            setThreadLogic(graphA, threadTimeWaiting);

            //graphAB
            GraphABThread graphAB = new GraphABThread("graphAB");
            setThreadLogic(graphAB, threadTimeWaiting);

            //graphBA
            GraphBAThread graphBA = new GraphBAThread("graphBA");
            setThreadLogic(graphBA, threadTimeWaiting);

            //graphB
            GraphBThread graphB = new GraphBThread("graphB");
            setThreadLogic(graphB, threadTimeWaiting);

            //graph aPatternSuccess
            GraphAPatternSuccessThread graphApatternSuccess = new GraphAPatternSuccessThread("aPatternSuccess");
            setThreadLogic(graphApatternSuccess, threadTimeWaiting);

            ///graph bPatternSuccess
            GraphBPatternSuccessThread graphBpatternSuccess = new GraphBPatternSuccessThread("bPatternSuccess");
            setThreadLogic(graphBpatternSuccess, threadTimeWaiting);

            //graph Breduction
            GraphReductionBThread graphBreduction = new GraphReductionBThread("graphBreduction");
            setThreadLogic(graphBreduction, threadTimeWaiting);

            long end = System.currentTimeMillis();
            System.out.println("End = " + (end - beggining));

            //        Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_REDUCTIONB);
            //        Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_REDUCTIONB);
            //        exists = false;
            //        do {
            //            exists = (new File(Const.GENERAL_OUTPUT.OUTPUT_REDUCTIONB)).exists();
            //        } while (exists == false);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(GenerateResults2latex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void setThreadLogic(final Thread thread, final int threadTimeWaiting) throws InterruptedException {
        thread.start();
        thread.join(threadTimeWaiting);
        if (thread.isAlive() == true) {
            printThreadInfo();
            thread.interrupt();
        }
    }

    private static void printThreadInfo() {
        System.out.println("\t Your time expired - finishing ");
    }
}
