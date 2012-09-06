/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foreign.eckel.bruce.exceptions;


/**
 *
 * @author bracek
 */
class Prepnuti {

    boolean stav = false;

    boolean cist() {
        return stav;
    }

    void zapnuto() {
        stav = true;
    }

    void vypnuto() {
        stav = false;
    }
}

class VyjimkaPriVypnuti1 extends Exception {
}

class VyjjimkaPriVypnuti2 extends Exception {
}

public class OnOffSwitch {

    static Prepnuti prep = new Prepnuti();

    static void f() throws VyjimkaPriVypnuti1, VyjjimkaPriVypnuti2 {
    }

    public static void main(String[] args) {
        try {
            System.out.println("som v try");
            prep.zapnuto();
            // kod ktory vyvola vyjimky
            f();
            prep.vypnuto();
        } catch (VyjimkaPriVypnuti1 e) {
            System.err.println("VyjimkaPriVypnuti1");
            prep.vypnuto();
        } catch (VyjjimkaPriVypnuti2 e) {
            System.err.println("VyjimkaPriVypnuti2");
            prep.vypnuto();
        }
    }
}
