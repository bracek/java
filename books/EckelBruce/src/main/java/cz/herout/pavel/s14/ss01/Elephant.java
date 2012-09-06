/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss01;

/**
 *
 * @author bracek
 */
public class Elephant extends Animal{
    
        private int lenghtTrunk;

    public Elephant(String typ, int lenghtTrun) {
        super(typ);
        this.lenghtTrunk = lenghtTrun;
    }
        

    /**
     * Get the value of lenghtTrun
     *
     * @return the value of lenghtTrun
     */
    public int getLenghtTrun() {
        return lenghtTrunk;
    }

    /**
     * Set the value of lenghtTrun
     *
     * @param lenghtTrun new value of lenghtTrun
     */
    public void setLenghtTrun(int lenghtTrun) {
        this.lenghtTrunk = lenghtTrun;
    }

    

    @Override
    public void listingLength() {
        System.out.println("lenghtTrun = " + lenghtTrunk);
    }

}
