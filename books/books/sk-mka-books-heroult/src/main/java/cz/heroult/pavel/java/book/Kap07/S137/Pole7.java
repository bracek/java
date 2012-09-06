package cz.heroult.pavel.java.book.Kap07.S137;

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

public class Pole7 {
  public static void main(String[] args) {
    final int RADKY   = 24;
    final int SLOUPCE = 80;

    byte[] obrazovka = new byte[RADKY * SLOUPCE];

    for (int i = 0;  i < RADKY;  i++) {
      for (int j = 0;  j < SLOUPCE;  j++) {
        obrazovka[i * SLOUPCE + j] = (byte) (i + j);
      }
    }
      
    System.out.println(obrazovka[18 * SLOUPCE + 23]);
  }
}
