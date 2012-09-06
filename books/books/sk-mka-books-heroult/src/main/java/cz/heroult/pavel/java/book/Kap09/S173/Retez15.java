package cz.heroult.pavel.java.book.Kap09.S173;

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

public class Retez15 {
  public static void main(String[] args) {
    String s;
    int i;

    System.out.println(Math.PI);   // 3.141592656589793
    s = String.valueOf(Math.PI);
    i = s.indexOf('.');
    s = s.substring(0, i + 6);
    System.out.println(s);         // 3.14159
  }
}
