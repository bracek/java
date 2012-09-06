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

public class CalendarDoporuceneFormatovani {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();

    SimpleDateFormat sdfEra = new SimpleDateFormat("G");
    String era = sdfEra.format(cal.getTime());
    System.out.println("ERA: " + era);

    SimpleDateFormat sdfEraAngl = new SimpleDateFormat("G", new Locale("en", "US"));
    String eraA = sdfEraAngl.format(cal.getTime());
    System.out.println("ERA (angl.): " + eraA);

    SimpleDateFormat sdfEraCes = new SimpleDateFormat("G", new Locale("cs", "CZ"));
    String eraC = sdfEraCes.format(cal.getTime());
    System.out.println("ERA (cesky): " + eraC);
  }
}
