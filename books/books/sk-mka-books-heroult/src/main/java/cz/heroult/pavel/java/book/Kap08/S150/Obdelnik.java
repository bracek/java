package cz.heroult.pavel.java.book.Kap08.S150;

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

  public Obdelnik(int sirka, int vyska) {
    this.sirka = sirka;
    this.vyska = vyska;
  }

  public Obdelnik(Obdelnik o) {
    this(o.sirka, o.vyska);
  }

  public Obdelnik() {
    this(1, 1);
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
    Obdelnik jiny = new Obdelnik(obd);
    Obdelnik jedn = new Obdelnik();

    System.out.println("Obvod je: " + obd.obvod());
    System.out.println("Obvod je: " + jiny.obvod());
    System.out.println("Obvod je: " + jedn.obvod());
  }
}
