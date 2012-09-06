package cz.heroult.pavel.java.book.Kap03.S57;

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

public class RozsirujiciKonverze {
  public static void main(String[] args) {
    short s = 10;
    byte b;
    int i;
    float f;
    i = s;
    f = s;
    // b = s;  // zužující konverze
    // chyba-possible loss of precision: short, required: byte
    b = (byte) s;
    System.out.println("s = " + s + ", b = " + b);
  }
}
