package cz.heroult.pavel.bohatstvoKnihoven.kap02;

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

public class AutoboxingAUnboxing {
  public static void main(String[] args) {
    ArrayList<Integer> ar = new ArrayList<Integer>();
    ar.add(1);                     // autoboxing 
    ar.add(new Integer(2));
    
    Integer pomInt = ar.get(0);
    int i = pomInt.intValue();
    System.out.println(i * 2);
    int j = ar.get(1);             // unboxing
    System.out.println(j * 2);  

    // mimo kolekci
    Integer i1 = new Integer(5);
    Integer i2 = 6;              // autoboxing 
    int j1 = i1.intValue();
    int j2 = i2;                 // unboxing
  }
}
