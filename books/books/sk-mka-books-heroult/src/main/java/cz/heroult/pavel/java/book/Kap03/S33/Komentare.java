package cz.heroult.pavel.java.book.Kap03.S33;

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

public class Komentare {

  /* metoda vypoète obsah kruhu
     parametrem je polomìr kružnice
     -- typický komentáø pøes více øádek */
  static double obsahKruhu(double r) {
    return r * r * Math.PI;
  }

  public static void main(String[] args) {
    int utrata;
    int pocetPiv = 5;

    utrata = pocetPiv * 15;  // typický jednoøádkový komentáø
    System.out.println("Utrata = " + utrata);

    utrata = pocetPiv * /* 15 */ 20;  /* 15 je pro desítku */
    System.out.println("Utrata = " + utrata);

    System.out.println("Obsah kruhu o polomeru 5 = " + obsahKruhu(5.0));
  }
}
