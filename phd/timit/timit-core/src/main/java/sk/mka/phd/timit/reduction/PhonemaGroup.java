package sk.mka.phd.timit.reduction;

import java.util.ArrayList;

/**
 *
 * @author bracek
 * @date Jun 19, 2010
 */
public class PhonemaGroup implements Comparable<PhonemaGroup> {

    /**
     * Contains list of phoneme which group represents
     */
    private final ArrayList<Object> phoneme;
    private boolean activatedDesiredOutput;
    private int index;

    public PhonemaGroup(ArrayList<Object> col) {
        this.index = -1;
        this.phoneme = col;
    }

    public ArrayList<Object> getPhoneme() {
        return phoneme;
    }

    public boolean isActivatedDesiredOutput() {
        return activatedDesiredOutput;
    }

    public void setActivatedDesiredOutput(boolean activatedDesiredOutput) {
        this.activatedDesiredOutput = activatedDesiredOutput;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(PhonemaGroup o) {
        return this.index - o.index;
    }
}
