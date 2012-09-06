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

public class GregorianCalendarPrestupnyRok {
  public static void main(String[] args) {
    GregorianCalendar gc = new GregorianCalendar();

    System.out.println("1600: " + gc.isLeapYear(1600));
    System.out.println("1700: " + gc.isLeapYear(1700));
    System.out.println("1800: " + gc.isLeapYear(1800));
    System.out.println("1900: " + gc.isLeapYear(1900));
    System.out.println("2000: " + gc.isLeapYear(2000));
    System.out.println("2003: " + gc.isLeapYear(2003));
  }
}
