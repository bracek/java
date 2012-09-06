package cz.heroult.pavel.bohatstvoKnihoven.kap10;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                   Java -- bohatstv� knihoven                //
//                II. opraven� a roz���en� vyd�n�              //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       Cti_me.txt                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.util.*;

public class MnozinoveOperace {
  public static void main(String[] argv) {
    Set<String> m1 = new HashSet<String>();
    Collections.addAll(m1, "1", "2", "3", "4");
    Set<String> m2 = new HashSet<String>();
    Collections.addAll(m2, "2", "3");
//    System.out.println("m1: " + m1);
//    System.out.println("m2: " + m2);
    
    if (m1.containsAll(m2) == true)
      System.out.println(m2 + " je podmnozinou " + m1);

    m2.add("5");
    Set<String> sjednoceni = new HashSet<String>(m1);
    sjednoceni.addAll(m2);
    System.out.println(sjednoceni + " je sjednocenim " + m1 + " a " + m2);

    Set<String> prunik = new HashSet<String>(m1);
    prunik.retainAll(m2);
    System.out.println(prunik + " je prunikem " + m1 + " a " + m2);

    Set<String> rozdil = new HashSet<String>(m1);
    rozdil.removeAll(m2);
    System.out.println(rozdil + " je rozdilem " + m1 + " a " + m2);

    Set<String> symetrickyRozdil = new HashSet<String>(m1);
    symetrickyRozdil.addAll(m2);
    Set<String> tmp = new HashSet<String>(m1);
    tmp.retainAll(m2);
    symetrickyRozdil.removeAll(tmp);
    System.out.println(symetrickyRozdil + " je symetrickym rozdilem " + m1 + " a " + m2);
  }
}


