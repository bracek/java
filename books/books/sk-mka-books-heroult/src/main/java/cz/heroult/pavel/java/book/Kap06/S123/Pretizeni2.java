package cz.heroult.pavel.java.book.Kap06.S123;

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

public class Pretizeni2 {
  static void tiskPenez(int koruny) {
    System.out.println("Cena: " + koruny + ",-- Kc");
  }

  static void tiskPenez(int koruny, int halere) {
    System.out.println("Cena: " + koruny + "," + halere + " Kc");
  }

  public static void main(String[] args) {
    tiskPenez(12);
    tiskPenez(12, 50);
  }
}
