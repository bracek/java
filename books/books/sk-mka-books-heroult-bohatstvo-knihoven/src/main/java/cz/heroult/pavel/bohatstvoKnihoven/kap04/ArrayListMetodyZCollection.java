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

public class ArrayListMetodyZCollection {

    public static void tiskni(String jmeno, ArrayList<String> ar) {
        int vel = ar.size();
        System.out.print(jmeno + " (" + vel + ") : ");
        for (int i = 0; i < vel; i++) {
            System.out.print("[" + i + "]=" + ar.get(i) + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("\nVytvoreni seznamu");
        ArrayList<String> ar1 = new ArrayList<String>();
        System.out.println("ar1 je prazdny: " + ar1.isEmpty());
        ar1.add("prvni");
        ar1.add("druhy");
        ar1.add("prvni");
        System.out.println("ar1 je prazdny: " + ar1.isEmpty());
        tiskni("ar1", ar1);

        System.out.println("\nPridavani a ubirani prvku");
        ArrayList<String> ar2 = new ArrayList<String>(ar1);
        ar2.add("treti");
        tiskni("ar2", ar2);
        ar2.remove("prvni");
        tiskni("ar2", ar2);
        ar2.removeAll(ar1);
        tiskni("ar2", ar2);
        ar2.addAll(ar1);
        tiskni("ar2", ar2);
//        System.out.println("ar1 = " + ar1);
//        System.out.println("ar2 = " + ar2);

        ar2.retainAll(ar1);
        tiskni("ar2", ar2);

        System.out.println("\nHledani prvku");
        ArrayList<String> ar3 = new ArrayList<String>(ar1);
        ar3.add("ctvrty");
        System.out.println("ar3 obsahuje 'paty': "
                + ar3.contains("paty"));
        System.out.println("ar3 obsahuje ar1: "
                + ar3.containsAll(ar1));

        System.out.println("\nPrevod na pole");
        Object[] s = ar1.toArray();
        for (int i = 0; i < s.length; i++) {
            System.out.print("[" + i + "]=" + (String) s[i] + ", ");
        }
        System.out.println();
    }
}
