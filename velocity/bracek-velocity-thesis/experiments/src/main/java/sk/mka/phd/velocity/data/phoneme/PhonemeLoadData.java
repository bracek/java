package sk.mka.phd.velocity.data.phoneme;

import java.util.List;

/**
 *
 * @author bracek
 * @date Jun 27, 2009
 */
public interface PhonemeLoadData {

    /**
     * @param filename
     * @return list of phonema, which is used in database TIMIT 
     */
    public List loadPhonemaList(final String filename);

   /**
    *
    * @return list of id for specific phoeneme
    */
    public List<Integer> getListOfPhonemeId();

    /**
     * rate general avarage, avarage for class A, avarage for class B
     * @return
     */
    public List rateAvarage();

    /**
     * 
     * @param phone list of phoneme
     * @param cols define, how many colums will be generated for phone
     * @return array of array in following pattern: id phoneme id phoneme (
     */
    public Object[][] modifyList(final List<String> phone,final  int cols);
}
