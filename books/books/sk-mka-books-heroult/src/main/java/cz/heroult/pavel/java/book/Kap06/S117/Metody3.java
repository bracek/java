package cz.heroult.pavel.java.book.Kap06.S117;

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

public class Metody3 {
  static void tiskPenez(int koruny) {
    System.out.println("Cena: " + koruny + " Kc");
  }

  public static void main(String[] args) { 
    int a = 100, b = 50;   
    tiskPenez(a + b);
    tiskPenez(10);
    tiskPenez(a);
  }
}
