/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.nadtriedapodtrieda;

/**
 *
 * @author bracek
 */
public class Podtrieda extends Nadtrieda {

    private int nPodtrieda = 1;

    public Podtrieda() {
        this.whoIam = "Podtrieda";
    }

//    @SuppressWarnings("unchecked, deprecation")
    @Override
    public void vypis() { //Prekrýva metódu vypis z triedy Nadtrieda
//        super.vypis();
        System.out.println("Vypísané v triede Podtrieda");
    }

    public static void hidding() {
        System.out.println("Podtrieda skryvanie, toto sa neodporuca ");
    }

    public static void main(String[] args) {

//       Podtrieda s = new Podtrieda();
//        s.vypis();

        Nadtrieda p = new Podtrieda(); //p je typu Nadtrieda aj Podtrieda, dovtedy kym do p sa nepriradi objekt, ktory nie je typom Podtrieda       
        p.ibaNadtrieda();
        p.vypis();
        p.hidding();

//        Nadtrieda a = new Nadtrieda();  // toto je zly priklad castovania, clascast exceptions <=> Nadtrieda je iba typu Nadrieda, nie je mozne
        //castovat na Podtrieda
//        Podtrieda pod1 = (Podtrieda) a;


        if (p instanceof Podtrieda) {
            Podtrieda pod = (Podtrieda) p;      //explicitny casting
            pod.vypis();

            System.out.println("pod.nPodtrieda = " + pod.nPodtrieda);
            System.out.println("pod.whoIam = " + pod.whoIam);

        }

        System.out.println("xxxx");
        Podtrieda subclass = new Podtrieda();



    }
}

