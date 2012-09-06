package cz.heroult.pavel.java.book.Kap06.S124;

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
  static void tiskni1() {
    System.out.println(i);
  }

  public static void main(String[] args) {
    tiskni1();
    tiskni2();
  }

  static int i = 5;

  static void tiskni2() {
    System.out.println(i);
  }
}
