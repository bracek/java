package cz.heroult.pavel.java.book.Kap03.S58;

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

public class ZuzujiciKonverze {
  public static void main(String[] args) {
    short s = 300;
    byte b;
    b = (byte) s;
    System.out.println("s = " + s + ", b = " + b);
    b = (byte) 255;
    System.out.println("b = " + b);
  }
}
