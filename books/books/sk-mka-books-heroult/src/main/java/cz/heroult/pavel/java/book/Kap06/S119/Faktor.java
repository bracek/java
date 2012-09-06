package cz.heroult.pavel.java.book.Kap06.S119;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojovı kód je souèástí distribuce balíku programù,  //
//     poskytovanıch jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        kterı je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

public class Faktor {
  public static void main(String[] args) {
    System.out.println("20! = " + fakt(20));
  }

  public static long fakt(long n) {
    if (n > 1) 
      return n * fakt(n - 1);
    else 
      return 1;
  }
}
