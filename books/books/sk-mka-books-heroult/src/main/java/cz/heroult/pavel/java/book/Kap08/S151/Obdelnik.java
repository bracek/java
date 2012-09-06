package cz.heroult.pavel.java.book.Kap08.S151;

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

  public void nastavSirku(int sirka) {
    this.sirka = sirka;
  }

  public Obdelnik(int sirka, int vyska) {
    nastavSirku(sirka);
    this.vyska = vyska;
  }

  public int obvod() {
    int sirka;
    sirka = 2 * (this.sirka + vyska);
    return sirka;
  }

  public int obsah() {
    return (sirka * vyska);
  }

  public static void main(String[] args) {
    Obdelnik obd = new Obdelnik(5, 3);
    obd.nastavSirku(6);
    System.out.println("Obvod je: " + obd.obvod());
  }
}
