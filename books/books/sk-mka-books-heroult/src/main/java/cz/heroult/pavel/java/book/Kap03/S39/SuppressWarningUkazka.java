package cz.heroult.pavel.java.book.Kap03.S39;

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

public class SuppressWarningUkazka {
//  @SuppressWarnings("fallthrough serial")
  public static void main(String[] args) {
    switch (1) {
      case 1:
        System.out.println("1");
        //  zde nema byt  break; 
      case 2:
        System.out.println("2");
    }
  }
}
