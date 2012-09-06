package sk.mka.phd.timit.reduction.writer;

import java.util.ArrayList;
import java.util.Arrays;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sk.mka.phd.dom4j.XmlWriter;
import sk.mka.phd.timit.maven.TimitParams;
import sk.mka.phd.timit.reduction.PhonemaGroup;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public class WritePhonemeReductionGroupToXml extends AbstractWritePhonemeReductionGroups implements IWritePhonemeReductionGroups {

    public WritePhonemeReductionGroupToXml() {
        super(TimitParams.getInputDirectory(TimitParams.TimitValues.groupsOutputXml));
    }

    private enum Elements {

        phoneme,
        oldPhoneme;
    }

    @Override
    public void write(final ArrayList<PhonemaGroup> groups) {

        final Document document = DocumentHelper.createDocument();
        final Elements phoneElement = Elements.phoneme;
        final Element catalogElement = document.addElement(phoneElement.toString());
        catalogElement.addComment("An phoneme list");
        String index;

        final StringBuffer stringBuffer = new StringBuffer();
        PhonemaGroup group;
        final Object[] groupsArray = groups.toArray();
        Arrays.sort(groupsArray);
        for (Object object : groupsArray) {
            if (object instanceof PhonemaGroup) {
                group = (PhonemaGroup) object;

                final Element phone = catalogElement.addElement(phoneElement.toString());
                index = String.valueOf(group.getIndex());
                phone.addText("\n");
                phone.addAttribute("id", index);
                stringBuffer.append(group.getIndex());
                ArrayList<Object> phonemeRepresentation = group.getPhoneme();

                for (Object phonemeId : phonemeRepresentation) {
                    final String phonema = instance.getPhonemeById(Integer.valueOf(phonemeId.toString()));
                    stringBuffer.append("\t" + phonema + "\t" + phonemeId + "\t\n");

                    final Elements phoneElementOld = Elements.oldPhoneme;
                    final Element oldPhoneme = phone.addElement(phoneElementOld.toString());
                    oldPhoneme.addAttribute("phonema", phonema);
                    oldPhoneme.addAttribute("oldPhonemaId", phonemeId.toString());
                    phone.addText("\n");
                }
            }
        }

        XmlWriter.writeXmlDocumentToFile(filename, document);
    }
}
