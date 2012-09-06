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

public class CalendarKumulativniZmenaVyssichPolozek {
  public static void main(String[] args) {
    Calendar cal = new GregorianCalendar(2002, Calendar.DECEMBER, 31, 23, 59, 59);

    SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss, d. MMMM yyyy");
    System.out.println(s.format(cal.getTime()));
    cal.add(Calendar.SECOND, 1);
    System.out.println(s.format(cal.getTime()));
  }
}
