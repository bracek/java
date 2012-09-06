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

public class TimeZoneNastaveni {
  public static void main(String[] args) {
    TimeZone tz = TimeZone.getDefault();
    System.out.println(tz.getDisplayName());
    System.out.println(tz.getID());

    tz = TimeZone.getTimeZone("Europe/Prague");
    System.out.println(tz.getDisplayName());
    tz = TimeZone.getTimeZone("Europe/Bratislava");
    System.out.println(tz.getDisplayName());
  }
}
