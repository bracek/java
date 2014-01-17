package sk.mka.phd.timit.reduction;

import sk.mka.phd.timit.reduction.writer.IWritePhonemeReductionGroups;
import sk.mka.phd.timit.reduction.writer.AbstractWritePhonemeReductionGroups;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import sk.mka.phd.constants.SeparatorConst;
import sk.mka.phd.files.writer.BufferFileWriter;
import sk.mka.phd.timit.maven.TimitParams;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public class WritePhonemeReductionGroupsLogFile extends AbstractWritePhonemeReductionGroups implements IWritePhonemeReductionGroups {

    final static Logger log = Logger.getLogger(WritePhonemeReductionGroupsLogFile.class);

    public WritePhonemeReductionGroupsLogFile() {
        super(TimitParams.getInputDirectory(TimitParams.TimitValues.groupsOutputRepresentation));
    }

    @Override
    public void write(final ArrayList<PhonemaGroup> groups) {
        String separator;

        try {
            final BufferedWriter out = BufferFileWriter.openFileForWriting(filename);
            final StringBuffer stringBuffer = new StringBuffer();
            PhonemaGroup group;
            Object[] groupsArray = groups.toArray();
            Arrays.sort(groupsArray);
            for (Object object : groupsArray) {
                if (object instanceof PhonemaGroup) {
                    group = (PhonemaGroup) object;

                    stringBuffer.append(group.getIndex());
                    log.info(group.getIndex());
                    ArrayList<Object> phonemeRepresentation = group.getPhoneme();

                    for (Object phonemeId : phonemeRepresentation) {
                        separator = SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue();

                        final String phonema = instance.getPhonemeById(Integer.valueOf(phonemeId.toString()));
                        stringBuffer.append(separator + phonema + separator + phonemeId + separator + "\n");
                        log.info(separator + phonema + separator + phonemeId + separator);
                    }
                }
            }
            out.write(stringBuffer.toString());
            out.close();
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
    }
}
