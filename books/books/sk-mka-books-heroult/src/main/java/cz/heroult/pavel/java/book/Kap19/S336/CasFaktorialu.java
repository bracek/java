package cz.heroult.pavel.java.book.Kap19.S336;

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

public class CasFaktorialu {
  final static int MAX = 5000000;
  public static void main(String[] args) {
    long z, k, f, zn, kn;

    for (long i = 10;  i <= 20;  i += 2) {
      System.out.print(i + "!\t");

      f = 0;

      z = System.currentTimeMillis();
      zn = System.nanoTime();

      for (int j = 1;  j <= MAX;  j++)
        f =+ Fakt.faktRek(i);

      kn = System.nanoTime();
      k = System.currentTimeMillis();
      System.out.print((k - z) + "\t");
      System.out.print(((kn - zn) / 1000000) + "\t");


      f = 0;

      z = System.currentTimeMillis();
      zn = System.nanoTime();

      for (int j = 1;  j <= MAX;  j++)
        f =+ Fakt.faktCykl(i);

      kn = System.nanoTime();
      k = System.currentTimeMillis();
      System.out.print((k - z) + "\t");
      System.out.println(((kn - zn) / 1000000) + "\t");
    }
  }
}
