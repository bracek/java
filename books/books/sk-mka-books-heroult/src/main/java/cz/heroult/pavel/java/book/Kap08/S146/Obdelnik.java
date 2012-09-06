package cz.heroult.pavel.java.book.Kap08.S146;

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

  public Obdelnik(int parSirka, int parVyska) {
    sirka = parSirka;
    vyska = parVyska;
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
    Obdelnik obd = new Obdelnik(1, 2);
    obd.vyska = 5;
    obd.sirka = 3;
    System.out.println("Obvod je: " + obd.obvod());

    Obdelnik obd2 = new Obdelnik(5, 3);
    System.out.println("Obdelnik vysky " + obd2.vyska + 
                       " a sirky " + obd2.sirka + 
                       " ma obvod: " + obd2.obvod());
  }
}
