package cz.heroult.pavel.java.book.Kap03.S62;

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

public class Preteceni {
  public static void main(String[] args) {
    byte b = 126;
    System.out.println("b = " + b);
    b += 3;
    System.out.println("b = " + b);
    b = -126;
    b += -5;
    System.out.println("b = " + b);
  }
}
