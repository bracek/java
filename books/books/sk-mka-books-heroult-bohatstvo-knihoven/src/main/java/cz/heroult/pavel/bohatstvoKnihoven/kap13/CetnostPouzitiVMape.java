package cz.heroult.pavel.bohatstvoKnihoven.kap13;

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

public class CetnostPouzitiVMape {
  public static void main(String[] args) {
    String[] pole = {"A", "B", "C", "D", "E", "F"};
    LinkedHashMap<String, Integer> pristup = 
       new LinkedHashMap<String, Integer>(16, 0.75F, true);
    for (int i = 0; i < pole.length;  i++) {
      pristup.put(pole[i], new Integer(0));
    }
    System.out.println("Po vytvoreni: " + pristup);

    Random r = new Random();
    for (int i = 0; i < 20;  i++) {
      String klic = "" + (char)(r.nextInt(6) + 'A');
      System.out.print(klic + " ");
      Integer hodnota = pristup.get(klic);
      pristup.put(klic, new Integer(hodnota.intValue() + 1));
    }
    System.out.println();
    System.out.println("Po pristupu:  " + pristup);
  }
}
