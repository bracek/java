package cz.heroult.pavel.java.book.Kap09.S178;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                  U�ebnice jazyka Java                       //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      //
//                       CTI_ME.TXT                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
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
