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

class Ovocie {

    int cena;
    String typ;

    Ovocie(int cena) {
        this.cena = cena;
        this.typ = "jablko";   // pro jednoduchost
    }

    @Override
    public String toString() {
        return typ + ":" + cena + " Kc";
    }

    @Override
    public int hashCode() {
        return cena;
    }
}

public class ChybneHesovaniNeprekrytaEquals {

    public static void main(String[] args) {
        System.out.println("Chybne hesovani");
        HashSet<Ovocie> OvocieSet = new HashSet<Ovocie>();
        for (int i = 6; i <= 8; i++) {
            OvocieSet.add(new Ovocie(i));
        }
        System.out.println("OvocieSet: " + OvocieSet);
        System.out.println("obsahuje 7: " + OvocieSet.contains(new Ovocie(7)));
    }
}
