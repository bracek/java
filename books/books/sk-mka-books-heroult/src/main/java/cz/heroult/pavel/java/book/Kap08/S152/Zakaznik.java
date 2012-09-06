package cz.heroult.pavel.java.book.Kap08.S152;

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

public class Zakaznik {
  static public int pocetZakazniku = 0;  // promenna tridy
  public int utratil;                    // promenna instance

  public Zakaznik() {
    Zakaznik.pocetZakazniku++;
    this.utratil = 0;
  }

  public void platil(int cena) {
    this.utratil += cena;
  }

  public static void main(String[] args) {
    System.out.println("Pocet zakazniku: " + pocetZakazniku);
    Zakaznik zak = new Zakaznik();
    System.out.println("Pocet zakazniku: " + pocetZakazniku);
    zak.platil(15);
    Zakaznik zak2 = new Zakaznik();
    System.out.println("Pocet zakazniku: " + pocetZakazniku);
    zak.platil(30);
    zak2.platil(20);
    System.out.println("Utratili: " + zak.utratil + " + " + zak2.utratil);

    pocetZakazniku++; // nesmysl, ale pøekladaèi nevadí
  }
}
