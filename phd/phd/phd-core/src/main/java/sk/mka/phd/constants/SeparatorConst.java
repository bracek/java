package sk.mka.phd.constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author katrami
 */
public class SeparatorConst {

    private Separator separator;
    private static final List<SeparatorConst> sepConstants = new ArrayList<SeparatorConst>();

    private SeparatorConst(final Separator separator) {
        this.separator = separator;
    }

    public Separator getSeparator() {
        return separator;
    }
    // Initialize prototype deck

    static {
        for (Separator suit : Separator.values()) {
            sepConstants.add(new SeparatorConst(suit));
        }
    }

    public static HashSet<SeparatorConst> newSepConstants() {
        return new HashSet<SeparatorConst>(sepConstants);
    }

    public void setSeparator(final Separator separator) {
        this.separator = separator;
    }

    public enum Separator {

        EQUALS_SEPARATOR("="),
        TABULATOR_SEPARATOR("\t");
        private final String separatorValue;

        Separator(final String separatorValue) {
            this.separatorValue = separatorValue;
        }

        public String getSeparatorValue() {
            return separatorValue;
        }
    }
}
