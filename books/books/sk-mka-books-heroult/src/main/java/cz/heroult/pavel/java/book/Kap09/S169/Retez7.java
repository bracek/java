package cz.heroult.pavel.java.book.Kap09.S169;

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

public class Retez7 {
  public static void main(String[] args) {
    String s2, s3, s1 = "mala a VELKA";

    s2 = s1.substring(5);
    s3 = s1.substring(5, 9);
    System.out.println(s2);  // "a VELKA"
    System.out.println(s3);  // "a VE"
  }
}
