package cz.heroult.pavel.java.book.Kap05.S103;

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

public class ForCarka5 {
  public static void main(String[] args) {
    for (int i = 1, sum = 0;  i <= 10;  i++)
       sum  +=  i;

    // chyba p�i p�ekladu, sum nen� deklarov�na
//    System.out.println(sum);
  }
}
