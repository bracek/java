package cz.heroult.pavel.java.book.Kap06.S119;

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

public class Fibon {
  public static void main(String[] args) {
    final int MAX = 42;
    long begin, end, f;

    for (long n = 0;  n <= MAX;  n += 5) {
      begin = System.currentTimeMillis();

      f = fib(n);

      end = System.currentTimeMillis();
      System.out.println("fib(" + n + ") = " + f +
                       " v case: " + (end - begin) + " [msec]");
    }
  }

  public static long fib(long n) {
    if (n == 0  ||  n == 1) {
      return n;
    }
    else {
      return fib(n - 1) + fib(n - 2);
    }
  }
}
