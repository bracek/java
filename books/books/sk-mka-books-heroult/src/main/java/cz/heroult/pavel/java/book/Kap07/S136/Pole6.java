package cz.heroult.pavel.java.book.Kap07.S136;

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

public class Pole6 {
  public static void main(String[] args) {
    int[][] b = {{ 1,  2,  3},
                 {11, 12, 13},
                 {21, 22, 23}};

    int[][] c = {{ 1,  2,  3},
                 {11, 12},
                 {21}};

    for (int i = 0;  i < b.length;  i++) {
      for (int j = 0;  j < b[i].length;  j++) {
        System.out.print(b[i][j] + "  ");
      }
      System.out.println();
    }

    System.out.println("-----------------");

    for (int i = 0;  i < c.length;  i++) {
      for (int j = 0;  j < c[i].length;  j++) {
        System.out.print(c[i][j] + "  ");
      }
      System.out.println();
    }
  }
}
