/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss03;

/**
 *
 * @author bracek
 */
public class Kufr implements Vanderable{

    int vaha;

    public Kufr(int vaha) {
        this.vaha = vaha;
    }
    
    public void listingWeight() {
        System.out.println("kufr = " + vaha);   
    }

}
