package cz.heroult.pavel.java.book.Kap06.S126;

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

public class Lokalni {
   static void tiskni() {
     int i = 6;
     System.out.println(i);
     {
  //     int i = 7;         // chyba - dvojnásobná deklarace
  //     long i = 7;        // chyba - dvojnásobná deklarace
       int j = 8;
       System.out.println(j);
     }
  //     System.out.println(j); // chyba - j není viditelná
    }


  static void tiskni2() {
    int i = 6;
    System.out.println(i);
 //   for (int i = 1;  i < 5;  i++)  // chyba
      System.out.println(i);
  }


  public static void main(String[] args) {
    tiskni();
    tiskni2();
  }
}
