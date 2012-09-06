package cz.heroult.pavel.java.book.Kap09.S180;

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

public class SplitRegPosixPismena {
  public static void main(String[] args) {
    String radka = "123a45A6";
    String [] podretezce = radka.split("\\p{Alpha}");
    for (int i = 0;  i < podretezce.length;  i++) {
      System.out.println("|" + podretezce[i] + "|");
    }
  }
}
