package sk.mka.phd.tools.math;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author katrami
 */
public class DecimalFormatMka {

    public static final String TWO_PATTERN_ACCURACY = "###.##";
    public static final String THREE_PATTERN_ACCURACY = "###.###";

    /**
     *
     * @param accuracyPattern to how much places should be double value cut
     * @return
     */
    public static final double doFormat(final String accuracyPattern,
 final double result) {
        double resultFormated = 0.0;
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat("###.##");
            resultFormated = df.parse(df.format(result)).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(DecimalFormatMka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultFormated;
    }
}
