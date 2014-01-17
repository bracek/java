package sk.mka.phd.velocity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.cons.SeparatorConst;
import sk.mka.phd.velocity.data.file.IFileLoadData;
import sk.mka.phd.data.ILoadData;
import sk.mka.phd.velocity.data.impl.LoadInputDataImpl;

/**
 *
 * @author katrami
 * This class rewrite output from file defined as constant: INPUT_EXPERIMENTS_RESULTS to output file in latex format.
 * Input file has to be written in following format for each line:  1	80.71	97.08	64.32
 * Output file is defined by variable: Const.OUTPUT.OUTPUT_TABLE_TEX
 *
 */
public class GenExperimentsResults2latex {

    public static final String INPUT_EXPERIMENTS_RESULTS = "input/experiments/experiments_results.txt";
    public static final String INPUT_EXPERIMENTS_TEX_VM = "input/experiments/table_tex_experiments.vm";
    private static org.apache.log4j.Logger log = Logger.getLogger(GenExperimentsResults2latex.class);

    public static void main(final String[] args) throws IOException {

        final ILoadData loadInputData = new LoadInputDataImpl();
        ArrayList<SeparatorConst> sepConstant = SeparatorConst.newSepConstants();
        for (SeparatorConst separatorConst : sepConstant) {
            System.out.println("separatorConst: " + separatorConst.getSeparator().getSeparatorValue());
        }

        loadInputData.setSeparator(SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue());
        final Object info[][] = ((IFileLoadData) loadInputData).loadDataAsObjects(INPUT_EXPERIMENTS_RESULTS);
        final VelocityEngine velocityEngine = new VelocityEngine();

        BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File(INPUT_EXPERIMENTS_TEX_VM)));
            final FileWriter fileWriter = new FileWriter(new File(Const.OUTPUT.TXT.OUTPUT_TABLE_TEX));

            // Output
            final Writer writer = new BufferedWriter(fileWriter);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("info", info);

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
}
