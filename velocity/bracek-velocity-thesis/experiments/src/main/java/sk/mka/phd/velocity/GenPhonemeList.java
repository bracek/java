package sk.mka.phd.velocity;

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
import sk.mka.phd.velocity.data.phoneme.PhonemeLoadData;
import sk.mka.phd.velocity.data.phoneme.PhonemeLoadDataImpl;

/**
 * Class writes phoneme representation of database TIIMIT to latex file in following form
 * id phoneme id phoneme id phoneme
 * @author katrami
 */
public class GenPhonemeList {

    private static final String TRAIN_PHONELIST_VM = "input/train_phonelist.vm";
    private static org.apache.log4j.Logger log = Logger.getLogger(GenerateTableVoicedUnvoiced.class);

    public static void main(final String[] args) throws IOException {

        final PhonemeLoadData loadInputData = new PhonemeLoadDataImpl();
        final List<String> phoneList = loadInputData.loadPhonemaList(Const.INPUT.PHONELIST);
        final Object phone[][] = loadInputData.modifyList(phoneList, 3);
        final VelocityEngine velocityEngine = new VelocityEngine();

        BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File(TRAIN_PHONELIST_VM)));
            final FileWriter fileWriter = new FileWriter(new File(Const.OUTPUT.TXT.OUTPUT_TABLE_TEX));

            // Output
            final Writer writer = new BufferedWriter(fileWriter);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("info", phone);

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
