package cz.heroult.pavel.java.book.Kap07.S135;

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

public class Pole4 {
  public static void main(String[] args) {
    int[][] a = new int[5][4];

    System.out.println("Pocet radek pole: " + a.length);
    System.out.println("Pocet sloupcu pole: " + a[0].length);

    for (int i = 0;  i < a.length;  i++) {
      for (int j = 0;  j < a[i].length;  j++) {
        a[i][j] = i * 10 + j;
        System.out.print(a[i][j] + "  ");
      }
      System.out.println();
    }
  }
}
