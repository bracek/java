package sk.mka.phd.velocity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.velocity.data.file.IFileLoadData;
import sk.mka.phd.velocity.data.impl.LoadVoicedVoicelessDataImpl;

public class GenerateTableVoicedUnvoiced {

    private static org.apache.log4j.Logger log = Logger.getLogger(GenerateTableVoicedUnvoiced.class);

    public static void main(String[] args) throws IOException {

        final IFileLoadData voicedVocelessList = new LoadVoicedVoicelessDataImpl();
        final Object oldInfo[][] = voicedVocelessList.loadDataAsObjects(Const.VOICED_UNVOICED_LIST);

        final Object info[][] = modifyList(oldInfo);
        final VelocityEngine velocityEngine = new VelocityEngine();

        BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File(Const.INPUT_TABLE_TEX_VM)));
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

    /**
     * @deprecated this is very hardcoded, please rewrite, rename
     * @param oldInfo
     * @return
     */
    private static Object[][] modifyList(Object[][] oldInfo) {
        Object[][] newInfo = new Object[30][6];

        int row = 0;
        int col = 0;
        for (int i = 0; i < oldInfo.length; i++) {

            Object[] strings = (Object[]) oldInfo[i];
            for (int j = 0; j < strings.length; j++) {
                int columnLength = strings.length;
                Object value = strings[j];

                if ((i % 2) == 0) {
                    newInfo[row][j] = value;
                } else {
                    col = columnLength + j;
                    newInfo[row][col] = value;
                }
            }

            if ((i % 2) != 0) {
                row++;
            }

        }
        return newInfo;
    }
}
