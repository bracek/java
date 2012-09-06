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

public class StejneMapyAPodmapy {
  static HashMap<String, Integer> vytvoreniANaplneniMapy() {
    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    for (int i = 1;  i <= 3;  i++) {
      String jmeno = "Robot" + i;
      hm.put(jmeno, new Integer(50 + i));
    }
    return hm;
  }
 
  public static void main(String[] args) {
    HashMap<String, Integer> hmOrig = vytvoreniANaplneniMapy();
    HashMap<String, Integer> hmStejna = vytvoreniANaplneniMapy();

    HashMap<String, Integer> hmMensi = vytvoreniANaplneniMapy();
    Set kl = hmMensi.keySet();
    kl.remove("Robot1");

    HashMap<String, Integer> hmVetsi = vytvoreniANaplneniMapy();
    hmVetsi.put("Robot5", new Integer(55)); 

    System.out.println("hmOrig:    " + hmOrig);

    boolean b;
    b = hmStejna.entrySet().containsAll(hmOrig.entrySet());
    System.out.println("hmStejna:  " + hmStejna);
    System.out.println("hmOrig obsahuje stejne prvky jako hmStejna: " + b);

    b = hmMensi.entrySet().containsAll(hmOrig.entrySet());
    System.out.println("hmMensi:   " + hmMensi);
    System.out.println("hmOrig obsahuje stejne prvky jako hmMensi: " + b);

    b = hmVetsi.entrySet().containsAll(hmOrig.entrySet());
    System.out.println("hmVetsi:   " + hmVetsi);
    System.out.println("hmOrig obsahuje stejne prvky jako hmVetsi: " + b);
  }
}
