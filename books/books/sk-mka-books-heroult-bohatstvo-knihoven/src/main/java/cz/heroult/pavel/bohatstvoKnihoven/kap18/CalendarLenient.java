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

public class CalendarLenient {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    cal.setLenient(true);
    cal.set(2003, Calendar.JANUARY, 32);
    System.out.print(cal.get(Calendar.DAY_OF_MONTH) + ".");
    System.out.println((cal.get(Calendar.MONTH) + 1) + ".");
    cal.setLenient(false);
    cal.set(2003, Calendar.JANUARY, 32);
    System.out.print(cal.get(Calendar.DAY_OF_MONTH) + ".");
  }
}
