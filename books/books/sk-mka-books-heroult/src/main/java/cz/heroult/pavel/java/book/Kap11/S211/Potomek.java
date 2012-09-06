package cz.heroult.pavel.java.book.Kap11.S211;

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
  public Rodic(int parI) { i = parI; }
//  public Rodic() { i = 5; }
}

public class Potomek extends Rodic {
  public Potomek() {
    super(8);
  }
  public static void main(String[] args) {
    Potomek pot = new Potomek();
  }
}
