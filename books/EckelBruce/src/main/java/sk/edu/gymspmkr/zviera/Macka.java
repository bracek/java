/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.zviera;

import sk.edu.gymspmkr.zviera.Zviera;

/**
 *
 * @author bracek
 */
public class Macka extends Zviera {

    public static void testMetodyTriedy() {
        System.out.println("Metóda triedy v triede Macka.");
    }

    @Override
    public void testMetodyInstancie() {
        System.out.println("Metóda inštancie v triede Macka.");
    }

    public static void main(String[] args) {

        Macka mojaMacka = new Macka();
        Zviera mojeZviera = mojaMacka; //implicitny casting

        Zviera zv = new Macka(); //implicitny casting, 
        // zv je tppu Object, Macka, Zviera dovtedy, kym do zv nie je priradeny iny objekt, ktory nie je typu Macka

        Macka m = (Macka) new Zviera();



        Zviera.testMetodyTriedy();
        mojeZviera.testMetodyInstancie();
    }
}


