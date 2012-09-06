package cz.heroult.pavel.java.book.Kap03.S70;

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

public class GrObjekt {
  public static void main(String[] args) {
    final byte VIDITELNY = 1;
    final byte PREMISTITELNY = 2;
    final byte MENITELNY = 4;
    final byte SMAZATELNY = 8;

    byte stav = 0;       // s tímto objektem nelze provádet nic
    stav |= VIDITELNY;   // od této chvíle je viditelný
    if ((stav & VIDITELNY) == VIDITELNY)
      System.out.println("je viditelny");

    stav &= ~VIDITELNY;      // od této chvíle je neviditelný
    if ((stav & VIDITELNY) == VIDITELNY)
      System.out.println("viditelny");
  }
}
