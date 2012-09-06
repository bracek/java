package cz.heroult.pavel.java.book.Kap06.S121;

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

public class Hodnotou {
  static int zmena(int i) {
    i++;
    return i;
  }

  public static void main(String[] args) {
    int j, k = 4;
    j = zmena(k);
    System.out.println("k = " + k + ", j = " + j);
  }
}
