package cz.heroult.pavel.java.book.Kap03.S72;

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

public class DrzeniZnamenka {
  public static void main(String[] args) {
    byte x = 16;
    x >>= 2;
    System.out.println("x = " + x);

    x = -16;
    x >>= 2;
    System.out.println("x = " + x);
  }
}
