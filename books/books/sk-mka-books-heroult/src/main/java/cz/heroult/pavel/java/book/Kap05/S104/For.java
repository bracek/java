package cz.heroult.pavel.java.book.Kap05.S104;

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

public class For {
  final static int N = 50000000;
  public static void main(String[] args) {
    long z, k;    
    int i, soucin;

    z = System.currentTimeMillis();

    for (i = 1, soucin = 1;  i <= N;  i++)
      if (i % 2 == 1)  // dìlení modulo pro zjištìní lichosti
         soucin *= i;

    k = System.currentTimeMillis();
    System.out.print("soucin = " + soucin);
    System.out.println(", cas = " + (k - z) + " [msec]");

    z = System.currentTimeMillis();
    for (i = 3, soucin = 1;  i <= N;  i += 2)
       soucin *= i;

    k = System.currentTimeMillis();
    System.out.print("soucin = " + soucin);
    System.out.println(", cas = " + (k - z) + " [msec]");
  }
}
