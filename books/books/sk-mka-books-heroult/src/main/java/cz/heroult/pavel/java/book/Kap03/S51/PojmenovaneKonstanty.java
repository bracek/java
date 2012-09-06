package cz.heroult.pavel.java.book.Kap03.S51;

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

public class PojmenovaneKonstanty {
  final static int ASISTENT         = 1;
  final static int ODBORNY_ASISTENT = 2;
  final static int DOCENT           = 3;
  final static int PROFESOR         = 4;
  
  public static void main(String[] args) {
    int vyucujici = DOCENT;

    if (vyucujici == DOCENT  ||  vyucujici == PROFESOR) {
      System.out.println("Muze vest doktorandy");
    }
    
    System.out.println("vyucujici: " + vyucujici);
    
    vyucujici *= 2;    // nesmysl, ale mozny
    System.out.println("vyucujici: " + vyucujici);
  }
}
