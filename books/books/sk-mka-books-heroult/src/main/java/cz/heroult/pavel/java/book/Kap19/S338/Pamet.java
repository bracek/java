package cz.heroult.pavel.java.book.Kap19.S338;

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

public class Pamet {
  public static void main(String[] args) {
    Runtime r = Runtime.getRuntime();

    System.out.println("Cela pamet: " + r.totalMemory());
    System.out.println("Volna pamet: " + r.freeMemory());

    int[] pole = new int[100000000];
    System.out.println("Volna pamet: " + r.freeMemory());
    pole = null;
    System.gc();
    System.out.println("Volna pamet: " + r.freeMemory());
  }
}
