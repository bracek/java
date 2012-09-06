package cz.heroult.pavel.java.book.Kap06.S120;

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

public class Konverze {
  static int konv1(double d) {    
    return (int) d;                
  }                               

  static double konv2(int d) {
    return d;
  }
  public static void main(String[] args) {
    int k = konv1(4);
    double j = konv2( (int) 4.5);
    System.out.println("k = " + k + ", j = " + j);
  }
}
