package sk.mka.phd.matlab.cons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katrami
 */
public class SeparatorConst {

    public Separator getSeparator() {
        return separator;
    }

    public void setSeparator(final Separator separator) {
        this.separator = separator;
    }
    private Separator separator;

    private SeparatorConst(final Separator separator) {
        this.separator = separator;
    }
    private static final List<SeparatorConst> sepConstants = new ArrayList<SeparatorConst>();

    // Initialize prototype deck
    static {
        for (Separator suit : Separator.values()) {
            sepConstants.add(new SeparatorConst(suit));
        }
    }

    public static ArrayList<SeparatorConst> newSepConstants() {
        return new ArrayList<SeparatorConst>(sepConstants);
    }

    public enum Separator {

        EQUALS_SEPARATOR("="),
        TABULATOR_SEPARATOR("\t");
        private final String separatorValue;

        Separator(String separatorValue) {
            this.separatorValue = separatorValue;
        }

        public String getSeparatorValue() {
            return separatorValue;
        }
    }
}
