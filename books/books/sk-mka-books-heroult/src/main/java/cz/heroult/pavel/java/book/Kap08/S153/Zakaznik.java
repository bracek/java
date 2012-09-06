package cz.heroult.pavel.java.book.Kap08.S153;

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
  static int pocetZakazniku = 0;  // promenna tridy
  public int utratil;             // promenna instance

  public Zakaznik() {
    pocetZakazniku++;
    this.utratil = 0;
  }

  public void platil(int cena) {
    this.utratil += cena;
  }

  public static int kolikZakazniku() {
    return pocetZakazniku;
  }

  public static void main(String[] args) {
    System.out.println("Pocet zakazniku: " + kolikZakazniku());
    Zakaznik zak1 = new Zakaznik();
    System.out.println("Pocet zakazniku: " + Zakaznik.kolikZakazniku());
    System.out.println("Pocet zakazniku: " + zak1.pocetZakazniku);
    System.out.println("Pocet zakazniku: " + zak1.kolikZakazniku());
  }
}
