package cz.heroult.pavel.java.book.Kap09.S173;

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

public class Retez16 {
  public static void main(String[] args) {
  int i = 254;

  System.out.println(Integer.toBinaryString(i));  // 11111110
  System.out.println(Integer.toOctalString(i));   // 376 
  System.out.println(Integer.toHexString(i));     // fe
  }
}
