package cz.heroult.pavel.java.book.Kap06.S122;

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

public class Pretizeni1 {
  static int ctverec(int i) {
    return i * i;
  }

  static double ctverec(double i) {
    return i * i;
  }

  //  static long ctverec(int i) { // chyba
  static long ctverec(long i) {
    return i * i;
  }

  public static void main(String[] args) {
    int j = ctverec(5);
    double d = ctverec(5.5);
    long l = ctverec(12345L);

    System.out.println("j = " + j + ", d = " + d + ", l = " + l);
  }
}
