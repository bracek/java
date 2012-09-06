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

public class CalendarPocatecniDenTydne {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();

    int prvni = cal.getFirstDayOfWeek();
    System.out.println("prvni = " + prvni);

    if (prvni == Calendar.SUNDAY)
      System.out.println("nedìle");
    if (prvni == Calendar.MONDAY)
      System.out.println("pondìlí");
  }
}
