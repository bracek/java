/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss03;

/**
 *
 * @author bracek
 */
public class TestPolymInterf {
    public static void main(String[] args) {
        int vahaLidi = 0;
        Vanderable[] kusJakoKus = new Vanderable[3];
        kusJakoKus[0] = new Fellow(100, "programator");
        kusJakoKus[1] = new Kufr(20);
        kusJakoKus[2] = new Fellow(51, "modelka");
        
        for (int i = 0; i < kusJakoKus.length; i++) {
            kusJakoKus[i].listingWeight();
            if (kusJakoKus[i] instanceof Fellow == true) {
                vahaLidi += ((Fellow)kusJakoKus[i]).getHeight();
                
            }
            
        }
        System.out.println("zivaVaha = " + vahaLidi);
    }

}
