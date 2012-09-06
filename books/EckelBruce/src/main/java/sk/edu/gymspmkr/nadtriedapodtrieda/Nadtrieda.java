/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.nadtriedapodtrieda;

/**
 *
 * @author bracek
 */
public class Nadtrieda {

    protected String whoIam = "Nadtrieda";

    public Nadtrieda() {
        System.out.println(". constructor Nadtrieda .");
    }

    public void ibaNadtrieda(){
        System.out.println(". iba v nadtriede .");
    }
    
    @SuppressWarnings("unchecked, deprecation")
//    @Deprecated
    public void vypis() {
        System.out.println("Vypísané v triede Nadtrieda.");
    }

    public static void hidding() {
        System.out.println("Nadtrieda skryvanie");
    }
}

