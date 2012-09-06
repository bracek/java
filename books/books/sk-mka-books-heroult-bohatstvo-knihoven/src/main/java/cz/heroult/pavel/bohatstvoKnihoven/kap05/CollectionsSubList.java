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

public class CollectionsSubList {

    public static void main(String[] args) {
        String[] vse = {"1", "2", "3", "1", "2", "3"};
        String[] cast = {"2", "3"};
        List<String> list = Arrays.asList(vse);
        System.out.println(list);
        List<String> subList = Arrays.asList(cast);
        int prvni = Collections.indexOfSubList(list, subList);
        int posledni = Collections.lastIndexOfSubList(list, subList);
        System.out.println(subList + " je na " + prvni + ". pozici");
        System.out.println(subList + " je na " + posledni + ". posledni pozici");
    }
}
