package cz.heroult.pavel.java.book.Kap20.S366;

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

public class TestPrKon {
  public static void main(String[] args) {
    Cteni ct1 = new Cteni("data10.txt");
    Producent vlPr1 = new Producent(ct1);
    Konzument vlKon1 = new Konzument(ct1);
    vlKon1.start();
    vlPr1.start();   // schválnì pozdìji než producent
  } 
}
