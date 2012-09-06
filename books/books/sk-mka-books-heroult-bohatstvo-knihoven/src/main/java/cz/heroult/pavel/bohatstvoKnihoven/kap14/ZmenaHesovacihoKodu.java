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

class Citac {

    int hodnota;

    Citac() {
        hodnota = 0;
    }

    void plusJedna() {
        hodnota++;
    }

    @Override
    public int hashCode() {
        return hodnota;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Citac == false) {
            return false;
        }

        boolean stejnaHodnota = (hodnota == ((Citac) o).hodnota);
        return stejnaHodnota;
    }
}

public class ZmenaHesovacihoKodu {

    public static void main(String[] args) {
        HashSet<Citac> hs = new HashSet<Citac>();
        Citac c = new Citac();
        Citac pom = new Citac();
        System.out.println("citace jsou si rovny: " + c.equals(pom));

        hs.add(c);
        System.out.println(hs);
        System.out.println("obsahuje 0: " + hs.contains(pom));
        pom.plusJedna();
        System.out.println(hs);
        System.out.println("obsahuje 0: " + hs.contains(pom));
    }
}
