package cz.heroult.pavel.bohatstvoKnihoven.kap18;

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

public class CalendarMaximaAMinima {
  public static void main(String[] args) {
    Calendar cal = new GregorianCalendar(2003, Calendar.FEBRUARY, 23);

    int amax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int max = cal.getMaximum(Calendar.DAY_OF_MONTH);
    System.out.println("amax = " + amax + ", max = " + max);

    int ymin = cal.getMinimum(Calendar.YEAR);
    int ymax = cal.getActualMaximum(Calendar.YEAR);
    System.out.println("ymin = " + ymin + ", ymax = " + ymax);
  }
}
