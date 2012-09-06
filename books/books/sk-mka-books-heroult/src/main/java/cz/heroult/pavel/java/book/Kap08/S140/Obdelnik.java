package cz.heroult.pavel.java.book.Kap08.S140;

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

public class Obdelnik {
  public int sirka;
  public int vyska;

  public int obvod() {
    int pom;
    pom = 2 * (sirka + vyska);
    return pom;
  }

  public int obsah() {
    return (sirka * vyska);
  }

  public static void main(String[] args) {
    Obdelnik obd = new Obdelnik();
    obd.vyska = 5;
    obd.sirka = 3;

    System.out.println("Obvod je: " + obd.obvod());
  }
}
