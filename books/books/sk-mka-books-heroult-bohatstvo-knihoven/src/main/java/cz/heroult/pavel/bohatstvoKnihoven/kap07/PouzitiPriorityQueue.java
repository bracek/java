package cz.heroult.pavel.bohatstvoKnihoven.kap07;


/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      //
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      //
//                                                             //
/////////////////////////////////////////////////////////////////
import java.util.*;

class Vojak implements Comparable<Vojak> {

    private int pocetHvezdicek;
    private String hodnost;
    private String typVojska;

    public Vojak(int pocetHvezdicek, String hodnost, String typVojska) {
        this.pocetHvezdicek = pocetHvezdicek;
        this.hodnost = hodnost;
        this.typVojska = typVojska;
    }

    // prirozene razeni je v armade sestupne ;-)
    public int compareTo(Vojak v) {
        return v.pocetHvezdicek - this.pocetHvezdicek;
    }

    public String toString() {
        return "\n" + pocetHvezdicek + " " + hodnost + " " + typVojska;
    }
}

public class PouzitiPriorityQueue {

    public static void main(String[] args) {
        Queue<Vojak> kantyna = new PriorityQueue<Vojak>();
        kantyna.add(new Vojak(1, "major", "letectvo"));
        kantyna.add(new Vojak(3, "plukovnik", "pechota"));
        kantyna.add(new Vojak(2, "podplukovnik", "policie"));
        kantyna.add(new Vojak(3, "plukovnik", "spojari"));
        kantyna.add(new Vojak(4, "nadplukovnik", "chemici"));
        System.out.println("Fronta najednou: " + kantyna);

        System.out.print("\nPoradi vydeje jidel:");
        while (kantyna.isEmpty() == false) {
            System.out.print(kantyna.remove());
        }
    }
}
