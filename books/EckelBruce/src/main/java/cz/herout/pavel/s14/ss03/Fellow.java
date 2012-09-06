/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss03;

/**
 *
 * @author bracek
 */
public class Fellow implements Vanderable {
    
    int vaha;
    String profese;

    public Fellow(int vaha, String profese) {
        this.vaha = vaha;
        this.profese = profese;
    }
    
           

    public void listingWeight() {
        System.out.println("profese = " + profese);
    }
    
    public int getHeight() {return vaha; } 

}
