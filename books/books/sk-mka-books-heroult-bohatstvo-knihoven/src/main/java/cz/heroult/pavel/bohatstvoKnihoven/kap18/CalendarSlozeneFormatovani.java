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

public class CalendarSlozeneFormatovani {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();

    int den = cal.get(Calendar.DAY_OF_MONTH);
    SimpleDateFormat sdfD = new SimpleDateFormat("MMMM");
    String dlouhyMesic = sdfD.format(cal.getTime());
    int rok = cal.get(Calendar.YEAR);
    System.out.println("Dnes je: " + den + ". " + dlouhyMesic + " " + rok);

    SimpleDateFormat sdfDatum = new SimpleDateFormat("d. MMMM yyyy");
    String datum = sdfDatum.format(cal.getTime());
    System.out.println("Dnes je: " + datum);
  }
}
