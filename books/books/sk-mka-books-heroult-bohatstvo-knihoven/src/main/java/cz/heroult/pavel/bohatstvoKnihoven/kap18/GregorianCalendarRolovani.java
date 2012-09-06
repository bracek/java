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
import java.text.*;

public class GregorianCalendarRolovani {
  public static void main(String[] args) {
    GregorianCalendar gc = new GregorianCalendar(2003, Calendar.JANUARY, 31);
    SimpleDateFormat s = new SimpleDateFormat("d. MMMM yyyy");
    System.out.println(s.format(gc.getTime()));
    gc.roll(Calendar.MONTH, true);
    System.out.println(s.format(gc.getTime()));
    gc.roll(Calendar.MONTH, true);
    System.out.println(s.format(gc.getTime()));

    gc.roll(Calendar.MONTH, false);
    System.out.println(s.format(gc.getTime()));
  }
}
