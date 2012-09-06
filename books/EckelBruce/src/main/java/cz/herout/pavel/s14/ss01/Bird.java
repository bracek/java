/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss01;

/**
 *
 * @author bracek
 */
public class Bird extends Animal{

    private int lengthWinds;

    public Bird(String typ, int lengthWinds) {
        super(typ);
        this.lengthWinds = lengthWinds;
    }

    /**
     * Get the value of lengthWinds
     *
     * @return the value of lengthWinds
     */
    public int getLengthWinds() {
        return lengthWinds;
    }

    /**
     * Set the value of lengthWinds
     *
     * @param lengthWinds new value of lengthWinds
     */
    public void setLengthWinds(int lengthWinds) {
        this.lengthWinds = lengthWinds;
    }
    

    @Override
    public void listingLength() {
        System.out.println("winds length: = " + lengthWinds); 
    }

}
