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

public class ArrayListMetodyZList {
  public static void tiskni(String jmeno, ArrayList<String> ar) {
    int vel = ar.size();
    System.out.print(jmeno + " (" + vel + ") : ");
    for (int i = 0;  i < vel;  i++) {
      System.out.print("[" + i + "]=" + ar.get(i) + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.println("\nVytvoreni seznamu");
    ArrayList<String> ar = new ArrayList<String>();
    ar.add("prvni");
    ar.add("druhy");
    ar.add("prvni");
    tiskni("ar", ar);

    System.out.println("\nPridavani, zmena a ubirani prvku");
    ar.add(2, "treti");
    tiskni("ar", ar);
    ar.set(1, "DRUHY");
    tiskni("ar", ar);
    ar.remove(2);
    tiskni("ar", ar);

    System.out.println("\nHledani prvku");
    ar.add("ctvrty");
    tiskni("ar", ar);
    System.out.println("'ctvrty' ma index: "
                      + ar.indexOf("ctvrty"));
    System.out.println("posledni 'prvni' ma index: "
                      + ar.lastIndexOf("prvni"));
  }
}
