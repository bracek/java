package cz.heroult.pavel.bohatstvoKnihoven.kap11;

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

public class NastaveniVMape {
  private static String[] key = {"pozadi", "popredi", "ramecek"};
  private static String[] hodDef = {"bila", "cerna", "modra"};
  private static String[] hodUziv = {null, "modra", "cervena"};

  static Map<String, String> options(String[] hodnoty) {
    Map<String, String> m = new HashMap<String, String>();
    for (int i = 0;  i < key.length;  i++) {
      if (hodnoty[i] != null) {
        m.put(key[i], hodnoty[i]);
      }
    }
    return m;
  }

  public static void main(String args[]) {
    Map<String, String> defaultNastaveni = options(hodDef);
    Map<String, String> uzivatelNastaveni = options(hodUziv);
    Map<String, String> platneNastaveni = 
                   new HashMap<String, String>(defaultNastaveni);
    platneNastaveni.putAll(uzivatelNastaveni);
    System.out.println("Default:  " + defaultNastaveni);
    System.out.println("Uzivatel: " + uzivatelNastaveni);
    System.out.println("Platne:   " + platneNastaveni);
  }
}
