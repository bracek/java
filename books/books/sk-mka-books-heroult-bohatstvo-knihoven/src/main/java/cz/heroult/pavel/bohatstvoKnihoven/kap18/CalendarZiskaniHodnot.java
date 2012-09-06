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

public class CalendarZiskaniHodnot {
  public static void p(String tisk) {
    System.out.println(tisk);
  }
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    p("ERA: " + cal.get(Calendar.ERA));
    p("YEAR: " + cal.get(Calendar.YEAR));
    p("MONTH: " + cal.get(Calendar.MONTH));
    p("DATE: " + cal.get(Calendar.DATE));
    p("DAY_OF_MONTH: " + cal.get(Calendar.DAY_OF_MONTH));
    p("DAY_OF_YEAR: " + cal.get(Calendar.DAY_OF_YEAR));
    p("DAY_OF_WEEK: " + cal.get(Calendar.DAY_OF_WEEK));
    p("AM_PM: " + cal.get(Calendar.AM_PM));
    p("HOUR: " + cal.get(Calendar.HOUR));
    p("HOUR_OF_DAY: " + cal.get(Calendar.HOUR_OF_DAY));
    p("MINUTE: " + cal.get(Calendar.MINUTE));
    p("SECOND: " + cal.get(Calendar.SECOND));
    p("MILLISECOND: " + cal.get(Calendar.MILLISECOND));
  }
}
