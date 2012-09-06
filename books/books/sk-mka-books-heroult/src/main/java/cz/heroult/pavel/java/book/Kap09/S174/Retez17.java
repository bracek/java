package cz.heroult.pavel.java.book.Kap09.S174;

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

public class Retez17 {
  public static void main(String[] args) {
    double d1 = Double.valueOf("3.14").doubleValue();
    double d2 = new Double("3.14").doubleValue();
    boolean b = Boolean.valueOf("true").booleanValue();
    int i = Integer.valueOf("123").intValue();

    System.out.println("d1 = " + d1);
    System.out.println("d2 = " + d2);
    System.out.println("b  = " + b);
    System.out.println("i  = " + i);
  }
}
