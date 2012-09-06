package sk.mka.phd.matlab.generator.voiced;


import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.generator.AbstractGenerator;
import sk.mka.phd.matlab.generator.Generator;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public class VoicedGeneratorImpl extends AbstractGenerator implements Generator {

    static final String EXCLUDE_ID_LIST = "input/voiced/voicedExcludeIdList.txt"; //list of id, which contains small number of vectors as (6, 9 less than 100

    public VoicedGeneratorImpl() {
        this.setWantVoiced(Const.VOICED_REPRESENTATION);
        loadSetExcludedIdList(EXCLUDE_ID_LIST);
    }
}
