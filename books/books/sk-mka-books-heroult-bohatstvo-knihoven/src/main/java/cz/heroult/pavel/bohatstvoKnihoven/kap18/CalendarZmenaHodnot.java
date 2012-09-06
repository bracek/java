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

public class CalendarZmenaHodnot {
  public static void main(String[] args) {
    Calendar cal = new GregorianCalendar(2003, Calendar.MARCH, 31);

    cal.setLenient(true);
    SimpleDateFormat sdfDatum = new SimpleDateFormat("d. MMMM yyyy");
    String datum = sdfDatum.format(cal.getTime());
    System.out.println("pred zmenou: " + datum);

    cal.set(Calendar.MONTH, Calendar.APRIL);
    datum = sdfDatum.format(cal.getTime());
    System.out.println("po zmene:    " + datum);

    cal.setLenient(false);
    cal.set(Calendar.MONTH, Calendar.APRIL);
    datum = sdfDatum.format(cal.getTime());
    System.out.println("po zmene (false): " + datum);
  }
}
