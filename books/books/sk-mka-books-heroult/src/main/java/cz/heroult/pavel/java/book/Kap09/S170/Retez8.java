package cz.heroult.pavel.java.book.Kap09.S170;

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

public class Retez8 {
  public static void main(String[] args) {
    String s = "mala a VELKA";
    char[] znaky = new char[10];

    s.getChars(2, 9, znaky, 0);
    System.out.println(znaky);  // "la a VE"
  }
}
