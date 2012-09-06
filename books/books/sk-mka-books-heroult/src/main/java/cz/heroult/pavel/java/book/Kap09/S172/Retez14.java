package cz.heroult.pavel.java.book.Kap09.S172;

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

public class Retez14 {
  public static void main(String[] args) {
    boolean b = true;
    int i = 1234567; 
    double d = Math.PI;
    String s;

    s = String.valueOf(b);
    System.out.println("b: " + s);
    s = String.valueOf(i);
    System.out.println("i: " + s);
    s = String.valueOf(d);
    System.out.println("d: " + s);
  }
}
