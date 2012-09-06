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

public class ListPevnaVelikost {

    public static void main(String[] args) {
        List<String> karty = Arrays.asList(new String[8]);
        System.out.println(karty);
        karty.set(0, "7");
        karty.set(1, "8");
        karty.set(6, "kral");
        karty.set(7, "eso");
        System.out.println(karty);
        karty.add("falesne eso");
    }
}
