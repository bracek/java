package sk.mka.phd.timit.reduction.writer;

import sk.mka.phd.constants.SeparatorConst;
import sk.mka.phd.timit.distribution.PhonemeDistribution;
import sk.mka.phd.timit.maven.TimitParams;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public abstract class AbstractWritePhonemeReductionGroups implements IWritePhonemeReductionGroups {

    final String finalPathPhonemeDistribution = TimitParams.getInputDirectory(TimitParams.TimitValues.phonemeDistribution);
    final String phonemaDistributionSeparator = SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue();
    protected PhonemeDistribution instance = PhonemeDistribution.getInstance(finalPathPhonemeDistribution, phonemaDistributionSeparator);
    protected String filename;

    public AbstractWritePhonemeReductionGroups(final String filename) {
        this.filename = filename;
    }
}
