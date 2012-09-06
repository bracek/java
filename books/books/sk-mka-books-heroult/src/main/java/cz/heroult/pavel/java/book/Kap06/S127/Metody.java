package cz.heroult.pavel.java.book.Kap06.S127;

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

public class Metody {
  static int i = 5;

  static void tiskni() {
    int i = 6;
    System.out.println(i);          // tiskne 6  
    System.out.println(Metody.i);   // tiskne 5
  }

  public static void main(String[] args) {
    tiskni();
  }
}
