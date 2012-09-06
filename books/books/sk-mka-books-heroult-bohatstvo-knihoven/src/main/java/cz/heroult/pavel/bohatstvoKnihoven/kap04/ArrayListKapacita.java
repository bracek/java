package cz.heroult.pavel.bohatstvoKnihoven.kap04;

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

public class ArrayListKapacita {

    public static final int POKUSU = 1000000;

    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<Integer>();
        long zac = System.currentTimeMillis();
        for (int i = 0; i < POKUSU; i++) {
            ar.add(new Integer(i));
        }
        long kon = System.currentTimeMillis();
        System.out.println("Trvani bez kapacity:      " + (kon - zac) + " [msec]");
        ar = null;
//    System.gc();

        ar = new ArrayList<Integer>(POKUSU);
        zac = System.currentTimeMillis();
        for (int i = 0; i < POKUSU; i++) {
            ar.add(new Integer(i));
        }
        kon = System.currentTimeMillis();
        System.out.println("Trvani s plnou kapacitou: " + (kon - zac) + " [msec]");
    }
}
