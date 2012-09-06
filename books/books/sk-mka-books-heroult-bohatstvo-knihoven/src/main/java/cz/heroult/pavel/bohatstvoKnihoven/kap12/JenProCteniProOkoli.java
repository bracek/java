package cz.heroult.pavel.bohatstvoKnihoven.kap12;

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

public class JenProCteniProOkoli {
  public static void main(String[] args) {
    ArrayList<Integer> pole = new ArrayList<Integer>();
    pole.add(new Integer(5));
    List<Integer> readOnlyPole = Collections.unmodifiableList(pole);
    System.out.println(readOnlyPole.get(0));
    pole.add(new Integer(6));      
    System.out.println(readOnlyPole.get(1));
  }
}
