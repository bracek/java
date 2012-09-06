package sk.mka.phd.timit.reduction.writer;

import java.util.ArrayList;
import sk.mka.phd.timit.reduction.PhonemaGroup;

/**
 *
 * @author bracek
 * @date Jul 6, 2010
 */
public interface IWritePhonemeReductionGroups {

    public void write(final ArrayList<PhonemaGroup> groups);
}
