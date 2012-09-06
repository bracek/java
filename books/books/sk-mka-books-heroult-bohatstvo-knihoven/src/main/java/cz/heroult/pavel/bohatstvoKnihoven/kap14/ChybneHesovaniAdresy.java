package cz.heroult.pavel.bohatstvoKnihoven.kap14;

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

class Ovoce {

    int cena;
    String typ;

    Ovoce(int cena) {
        this.cena = cena;
        this.typ = "jablko";   // pro jednoduchost
    }

    @Override
    public String toString() {
        return typ + ":" + cena + " Kc";
    }
}

public class ChybneHesovaniAdresy {

    public static void main(String[] args) {
        HashSet<Ovoce> ovoceSet = new HashSet<Ovoce>();
        for (int i = 6; i <= 8; i++) {
            ovoceSet.add(new Ovoce(i));
        }
        System.out.println("ovoceSet: " + ovoceSet);

        for (Ovoce o : ovoceSet) {
            System.out.print(o.hashCode() + ", ");
        }
        System.out.println();
        System.out.println(new Ovoce(7).hashCode());
        System.out.println("Integer: " + new Integer(7).hashCode());

        
        


    }
}
