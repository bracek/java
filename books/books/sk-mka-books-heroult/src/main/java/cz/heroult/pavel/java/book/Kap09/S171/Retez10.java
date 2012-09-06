package cz.heroult.pavel.java.book.Kap09.S171;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

public class Retez10 {
  public static void main(String[] args) {
    String s2, s1 = "\r\n\t ahoj\t \r\n";

    s2 = s1.trim();
    System.out.println("Zacatek:" + s1 + ":konec");
    System.out.println("Zacatek:" + s2 + ":konec");
  }
}
