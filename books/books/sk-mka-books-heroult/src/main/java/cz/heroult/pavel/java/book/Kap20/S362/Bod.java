package cz.heroult.pavel.java.book.Kap20.S362;

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

public class Bod {
  private int[] xy = {0, 0};

  public void nastav(int x, int y) {
    xy[0] = x;    // kritické místo
    xy[1] = y;
  }

  public int[] cti() {
    return new int[] {xy[0], xy[1]};
  }
}
