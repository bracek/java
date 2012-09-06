/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss02;

/**
 *
 * @author bracek
 */
public class Elephant extends Animal{
    
    private int lenghtTrunk;

    public Elephant(String type, int lenghtTrunk) {
        super(type);
        this.lenghtTrunk = lenghtTrunk;
    }

 
    @Override
    public void listingInfo() {
        super.listingInfo();
        System.out.println("lenghtTrunk = " + lenghtTrunk);
    }
    

    /**
     * Get the value of lenghtTrunk
     *
     * @return the value of lenghtTrunk
     */
    public int getLenghtTrunk() {
        return lenghtTrunk;
    }

    /**
     * Set the value of lenghtTrunk
     *
     * @param lenghtTrunk new value of lenghtTrunk
     */
    public void setLenghtTrunk(int lenghtTrunk) {
        this.lenghtTrunk = lenghtTrunk;
    }


}
