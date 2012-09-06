package cz.heroult.pavel.java.book.Kap03.S73;

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

public class NedrzeniZnamenka {
  public static void main(String[] args) {
    int x = 16;
    x >>>= 2;
    System.out.println("x = " + x);

    x = -16;
    System.out.println("x = " + Integer.toBinaryString(x));
    x >>>= 2;
    System.out.println("x = " + Integer.toBinaryString(x));
  }
}
