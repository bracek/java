package cz.heroult.pavel.java.book.Kap13.S244;

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

public class TestKoule {
  public static void main(String[] args) {
    Koule k = new Koule(3);
    Info i = new Usecka(5);
    i.kdoJsem();
    i = k;
    i.kdoJsem();
  }
}
