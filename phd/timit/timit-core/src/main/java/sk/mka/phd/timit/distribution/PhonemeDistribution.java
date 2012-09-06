package sk.mka.phd.timit.distribution;

import java.util.HashMap;
import sk.mka.phd.files.reader.load.square.AbstractSquareDataLoadFile;

/**
 * Class represents phoneme first 
 * @author katrami
 * @date Jun 18, 2010
 */
public class PhonemeDistribution extends AbstractSquareDataLoadFile implements IPhonemeDistribution {

    private static PhonemeDistribution instancePhonemeDistribution;
    private HashMap<Integer, String> phonemeDistribution = new HashMap<Integer, String>();

    public PhonemeDistribution(String finalPath, String separator) {
        super(finalPath, separator);
        this.phonemeDistribution = getPhonemeDistribution();
    }

    public static synchronized PhonemeDistribution getInstance(final String finalPath, final String separator) {
        if (instancePhonemeDistribution == null) {
            instancePhonemeDistribution = new PhonemeDistribution(finalPath, separator);
        }
        return instancePhonemeDistribution;
    }

    public String getPhonemeById(final Integer key) {
        return phonemeDistribution.get(key);
    }

    public HashMap<Integer, String> getPhonemeDistribution() {
        String[][] dirtyData = getValues();
        for (String[] strings : dirtyData) {
            Integer key = Integer.valueOf(strings[0]);
            phonemeDistribution.put(key, strings[1]);
        }
        return phonemeDistribution;
    }
}
