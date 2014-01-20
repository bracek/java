package sk.mka.phd.timit.reduction;

import sk.mka.phd.timit.reduction.writer.WritePhonemeReductionGroupToXml;
import sk.mka.phd.timit.reduction.writer.IWritePhonemeReductionGroups;
import java.util.ArrayList;
import java.util.Collection;
import sk.mka.phd.files.reader.load.arbitrary.AbstractArbitraryDataLoadFile;

/**
 *
 * @author katrami
 * @date Jun 18, 2010
 */
public class PhonemeReductionGroups extends AbstractArbitraryDataLoadFile implements IPhonemeReductionGroups {

    private Collection<PhonemaGroup> phonemesGroups;
    private IWritePhonemeReductionGroups outputFile;

    public PhonemeReductionGroups(final String finalPath,
 final String separator) {
        super(finalPath, separator);
        phonemesGroups = new ArrayList<PhonemaGroup>();
        loadReductionGroup();
        outputFile = new WritePhonemeReductionGroupsLogFile();
    }

    @Override
    public void printData() {
        ArrayList<PhonemaGroup> groups = this.getPhonemesGroups();
        outputFile.write(groups);

        outputFile = new WritePhonemeReductionGroupToXml();
        outputFile.write(groups);
    }

    @Override
    public ArrayList<PhonemaGroup> getPhonemesGroups() {
        return (ArrayList<PhonemaGroup>) phonemesGroups;
    }

    public PhonemaGroup getGroupByPhonema(final int i) {
        Integer phoneId;

        for (PhonemaGroup group : phonemesGroups) {
            ArrayList<Object> phonemeRepresentation = group.getPhoneme();
            for (Object phonemeId : phonemeRepresentation) {
                phoneId = Integer.valueOf(phonemeId.toString());
                if (phoneId == i) {
                    return group;
                }
            }
        }
        return null;
    }

    public void add(final PhonemaGroup phoneGroup) {
        phonemesGroups.add(phoneGroup);
    }

    private void loadReductionGroup() {

        for (Object object : linesArrayList) {
            ArrayList<Object> col = (ArrayList<Object>) object;
            PhonemaGroup phonemaGroup = new PhonemaGroup(col);
            this.phonemesGroups.add(phonemaGroup);
        }
    }
}
