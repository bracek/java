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

public class CalendarKumulativniZmenaNizsiPolozky {
  public static void main(String[] args) {
    Calendar cal1 = new GregorianCalendar(2003, Calendar.JANUARY, 31);
    Calendar cal2 = (Calendar) cal1.clone();

    SimpleDateFormat s = new SimpleDateFormat("d. MMMM yyyy");
    System.out.println(s.format(cal1.getTime()));
    cal1.add(Calendar.MONTH, 1);
    System.out.println(s.format(cal1.getTime()));
    cal1.add(Calendar.MONTH, 1);
    System.out.println(s.format(cal1.getTime()));
    cal2.add(Calendar.MONTH, 2);
    System.out.println(s.format(cal2.getTime()));
  }
}
