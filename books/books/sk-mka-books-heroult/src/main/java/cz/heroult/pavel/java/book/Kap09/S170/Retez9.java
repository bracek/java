package cz.heroult.pavel.java.book.Kap09.S170;

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

public class Retez9 {
  public static void main(String[] args) {
    String s = "mala a VELKA";

    if (s.startsWith("mala") == true)
      System.out.println("Zacina na \"mala\"");
    if (s.endsWith("mala") == false)
      System.out.println("Nekonci na \"mala\"");
  }
}
