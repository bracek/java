package cz.heroult.pavel.bohatstvoKnihoven.kap08;

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

public class TestListIterator {

    public static void main(String[] argv) {
        String[] tmp = {"1", "2", "3", "4", "5"};
        List<String> list = new ArrayList<String>(Arrays.asList(tmp));
        System.out.println("Seznam:          " + list);

        System.out.print("Seznam pozpatku: [");
        for (ListIterator<String> it = list.listIterator(list.size());
                it.hasPrevious();) {
            System.out.print(it.previous() + ", ");
        }
        System.out.println("]");
    }
}


