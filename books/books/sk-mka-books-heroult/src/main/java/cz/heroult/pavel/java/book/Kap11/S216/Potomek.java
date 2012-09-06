package cz.heroult.pavel.java.book.Kap11.S216;

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

class Rodic {
  public int i;
  static public long j;
}

public class Potomek extends Rodic {
  public long i;
  static public int j;
  public Potomek(long noveI) {
    i = noveI;
    super.i = 5;
  }
  public static void main(String[] args) {
    Rodic.j = 6;
    Potomek.j = 7;
  }
}
