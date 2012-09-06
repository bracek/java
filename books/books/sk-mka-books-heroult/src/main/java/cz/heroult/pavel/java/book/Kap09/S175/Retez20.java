package cz.heroult.pavel.java.book.Kap09.S175;

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

public class Retez20 {
  public static void main(String[] args) {
    int i;

    i = "dlouhy retezec".indexOf('y');
    System.out.println("i = " + i);
    String s = "obr".concat(String.valueOf(i)).concat(".jpg");    
    System.out.println("s = " + s);
  }
}
