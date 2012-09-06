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

public class PrevodCollectionNaArray {

    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        c.add("prvni");
        c.add("druhy");
        c.add("treti");
        System.out.println("kolekce:       " + c);
        Object[] o = c.toArray();
        System.out.println("Object:        " + Arrays.toString(o));

//    String[] s = (String[]) c.toArray();  // ClassCastException

        String[] s1 = c.toArray(new String[0]);
        System.out.println("String trik:   " + Arrays.toString(s1));
        String[] s2 = c.toArray(new String[2]);
        System.out.println("String mene:   " + Arrays.toString(s2));
        String[] s3 = c.toArray(new String[c.size()]);
        System.out.println("String presne: " + Arrays.toString(s3));
        String[] s4 = c.toArray(new String[5]);
        System.out.println("String vice:   " + Arrays.toString(s4));
    }
}
