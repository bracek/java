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

public class AsListVelikost {
  public static void main(String[] args) {
    String[] pole = {"1", "2", "3"};
    List<String> seznam = Arrays.asList(pole);
    System.out.println(seznam);
//    seznam.remove(0);
//    seznam.add("5");
    pole[1] = "osm";
    System.out.println(seznam);
  }
}
