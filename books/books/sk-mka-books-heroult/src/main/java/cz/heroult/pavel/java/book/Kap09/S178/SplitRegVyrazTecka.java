package cz.heroult.pavel.java.book.Kap09.S178;

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

public class SplitRegVyrazTecka {
  public static void main(String[] args) {
    String radka = "soubor.txt";
//    String [] podretezce = radka.split(".");
    String [] podretezce = radka.split("[.]");
    for (int i = 0;  i < podretezce.length;  i++) {
      System.out.println("|" + podretezce[i] + "|");
    }
  }
}
