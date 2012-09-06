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

public class CalendarNevhodneFormatovani {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    cal.set(2003, Calendar.JUNE, 14);

    String den = "";
    switch (cal.get(Calendar.DAY_OF_WEEK)) {
      case Calendar.MONDAY:    den = "Pondìlí"; break;
      case Calendar.TUESDAY:   den = "Úterý";   break;
      case Calendar.WEDNESDAY: den = "Støeda";  break;
      case Calendar.THURSDAY:  den = "Ètvrtek"; break;
      case Calendar.FRIDAY:    den = "Pátek";   break;
      case Calendar.SATURDAY:  den = "Sobota";  break;
      case Calendar.SUNDAY:    den = "Nedìle";  break;
    }
    System.out.println("DAY_OF_WEEK: " + den);
  }
}
