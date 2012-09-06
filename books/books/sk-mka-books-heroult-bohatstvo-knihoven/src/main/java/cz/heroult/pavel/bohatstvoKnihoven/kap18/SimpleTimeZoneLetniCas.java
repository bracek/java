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

public class SimpleTimeZoneLetniCas {
  public static void main(String[] args) {
    SimpleDateFormat sdf = new SimpleDateFormat("EE d. MM. yyyy");
    GregorianCalendar gc = new GregorianCalendar(2003, Calendar.JANUARY, 1);
    boolean zmena = true;

    for (int i = 1;  i < 365;  i++) {
      TimeZone tz = gc.getTimeZone();
      if (tz.inDaylightTime(gc.getTime()) == zmena) {
        GregorianCalendar gcv = (GregorianCalendar) gc.clone();
        gcv.add(Calendar.DAY_OF_YEAR, -1);  // predchozi den
        System.out.println(sdf.format(gcv.getTime()));
        zmena = !zmena;
      }
      gc.add(Calendar.DAY_OF_YEAR, 1);
    }
  }
}
