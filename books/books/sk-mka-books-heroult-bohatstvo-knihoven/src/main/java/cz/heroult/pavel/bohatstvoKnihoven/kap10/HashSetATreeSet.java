package cz.heroult.pavel.bohatstvoKnihoven.kap10;

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

public class HashSetATreeSet {

  public static void naplneniATisk(Set<String> st) {
    st.add("treti");
    st.add("prvni");
    st.add("druhy");
    // pokus o vlozeni stejneho prvku
    if (st.add("treti") == false) {
      System.out.println("'treti' podruhe nevlozen");
    }
    System.out.println(st.size() + " " + st);
    for (String s: st) {
      System.out.print(s + ", ");
    }
    if (st.contains("treti") == true) {
      System.out.println("\n'treti' je v mnozine");
    }
    st.remove("treti");
    System.out.println(st);
    st.clear();
  }

  public static void main(String[] args) {
    System.out.println("HashSet:");
    naplneniATisk(new HashSet<String>());
    System.out.println("TreeSet:");
    naplneniATisk(new TreeSet<String>());
  }
}
