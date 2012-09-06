package cz.heroult.pavel.bohatstvoKnihoven.kap08;

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

class Hruska1 {

    private int cena;

    Hruska1(int cena) {
        this.cena = cena;
    }

    public String toString() {
        return "" + cena;
    }

    public void tisk() {
        System.out.print(cena + ", ");
    }
}

public class IteratorZakladniPouziti {

    public static void main(String[] args) {
        ArrayList<Hruska1> kosHrusek = new ArrayList<Hruska1>();
        for (int i = 0; i < 10; i++) {
            kosHrusek.add(new Hruska1(i + 20));
        }

        for (Iterator<Hruska1> it = kosHrusek.iterator(); it.hasNext();) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();

        Iterator<Hruska1> it = kosHrusek.iterator();
        while (it.hasNext()) {
            it.next().tisk();
        }
        System.out.println();
    }
}
 
