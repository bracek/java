package sk.mka.phd.velocity.data;

import java.util.List;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public interface PhonemeLoadData {

    /**
     *
     * @param filename
     * @return list of phonema, which is used in database TIMIT 
     */
    public List loadPhonemaList(String filename);

    public List<Integer> getNnId();

    public List<String> getGeneralSuccess();

    /**
     * rate general avarage, avarage for class A, avarage for class B
     * @return
     */
    public List rateAvarage();
}
