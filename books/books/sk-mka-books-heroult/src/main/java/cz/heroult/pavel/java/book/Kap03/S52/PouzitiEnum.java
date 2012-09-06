package cz.heroult.pavel.java.book.Kap03.S52;

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

public class PouzitiEnum {
  enum Vyucujici {ASISTENT, ODBORNY_ASISTENT,
                  DOCENT, PROFESOR};
                 
  public static void main(String[] args) {
    Vyucujici vyucujici = Vyucujici.DOCENT;

    if (vyucujici == Vyucujici.DOCENT  
       ||  vyucujici == Vyucujici.PROFESOR) {
      System.out.println("Muze vest doktorandy");
    }
    
    System.out.println("vyucujici: " + vyucujici);

/* nelze prelozit    
    vyucujici *= 2;
    System.out.println("vyucujici: " + vyucujici);
*/    
  }
}
