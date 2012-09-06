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

public class CalendarPrehledVzoru {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();

    SimpleDateFormat sdfD = new SimpleDateFormat("MMMM");
    String dlouhyMesic = sdfD.format(cal.getTime());
    System.out.println("MMMM: " + dlouhyMesic);

    SimpleDateFormat sdfK = new SimpleDateFormat("MMM");
    String kratkyMesic = sdfK.format(cal.getTime());
    System.out.println("MMM:  " + kratkyMesic);

    SimpleDateFormat sdfM = new SimpleDateFormat("M");
    String mesicCislem = sdfM.format(cal.getTime());
    System.out.println("M:    " + mesicCislem);

    SimpleDateFormat sdfDD = new SimpleDateFormat("EEEE");
    String dlouhyDen = sdfDD.format(cal.getTime());
    System.out.println("EEEE: " + dlouhyDen);

    SimpleDateFormat sdfKD = new SimpleDateFormat("E");
    String kratkyDen = sdfKD.format(cal.getTime());
    System.out.println("E:    " + kratkyDen);

    SimpleDateFormat sdfAMPM = new SimpleDateFormat("a");
    String ampm = sdfAMPM.format(cal.getTime());
    System.out.println("AM_PM: " + ampm);
  }
}
