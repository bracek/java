package sk.mka.phd.matlab.generator.unvoiced;

import sk.mka.phd.matlab.generator.Generator;
import sk.mka.phd.matlab.generator.AbstractGenerator;
import java.io.File;

import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.file.PhonemeFileReader;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public class UnvoicedGeneratorImpl extends AbstractGenerator implements Generator {

    static final String EXCLUDE_ID_LIST = "input/unvoiced/unvoicedExcludedIdList.txt";

    public UnvoicedGeneratorImpl() {
        this.setWantVoiced(Const.UNVOICED_REPRESENTATION);
        loadSetExcludedIdList(EXCLUDE_ID_LIST);
    }
}
