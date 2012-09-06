package cz.heroult.pavel.bohatstvoKnihoven.kap05;

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

public class CollectionsSwap {
  public static void main(String[] args) {
    String[] vse = {"1", "2", "3", "4", "5", "6"};
    List<String> list = Arrays.asList(vse);
    System.out.println("puvodni   " + list);
    Collections.swap(list, 2, 5);
    System.out.println("po vymene " + list);
    Collections.swap(list, 0, 20);
    System.out.println("po vymene " + list);
  }
}
