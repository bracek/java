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

public class KontrolaAtributuVMape {
  public static void main(String args[]) {
    String[] key = {"pozadi", "popredi", "ramecek"};
    HashSet<String> atributy = new HashSet<String>(Arrays.asList(key));

    HashMap<String, String> nastaveni = new HashMap<String, String>();
    nastaveni.put("pozadi", "bila");
    // neznamy atribut
    nastaveni.put("text", "zelena");

    System.out.println("Seznam atributu:    " + atributy);
    System.out.println("Aktualni nastaveni: " + nastaveni);

    HashSet<String> chybi = new HashSet<String>(atributy);
    chybi.removeAll(nastaveni.keySet());
    System.out.println("Atributy chybi:     " + chybi);

    HashSet<String> navic = new HashSet<String>(nastaveni.keySet());
    navic.removeAll(atributy);
    System.out.println("Atributy navic:     " + navic);

    HashSet<String> shodne = new HashSet<String>(nastaveni.keySet());
    shodne.retainAll(atributy);
    System.out.println("Atributy shodne:    " + shodne);
  }
}
