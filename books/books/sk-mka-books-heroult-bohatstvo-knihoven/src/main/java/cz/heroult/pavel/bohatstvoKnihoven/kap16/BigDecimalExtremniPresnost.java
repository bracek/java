package cz.heroult.pavel.bohatstvoKnihoven.kap16;

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
import java.math.*;

public class BigDecimalExtremniPresnost {

    public static void main(String[] args) {
        BigDecimal pi;
        pi = new BigDecimal("3.14159265358979323846264338327");
        BigDecimal pi2 = pi.multiply(pi);
        BigDecimal pi1 = pi2.divide(pi, BigDecimal.ROUND_HALF_UP);
        System.out.println("Pi      = " + pi);
        System.out.println("Pi ** 2 = " + pi2);
        System.out.println("Pi      = " + pi1);
    }
}

