package cz.heroult.pavel.java.book.Kap19.S329;

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

public class Parametry {
  public static void main(String[] args) {
    Parametry ja = new Parametry();
    System.out.println("Program: " + ja.getClass().getName());
    for (int i = 0;  i < args.length;  i++)
      System.out.println(args[i]);
  }
}
