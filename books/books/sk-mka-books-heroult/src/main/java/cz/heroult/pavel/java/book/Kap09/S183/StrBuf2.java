package cz.heroult.pavel.java.book.Kap09.S183;

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

public class StrBuf2 {
  public static void main(String[] args) {
    StringBuffer b;

    b = new StringBuffer("Ahoj");
    System.out.println(b);    // Ahoj
    b.reverse();
    System.out.println(b);    // johA
  }
}
