package cz.heroult.pavel.java.book.Kap08.S162;

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

class Zakaznik {
  static int pocetZakazniku = 0;  // promenna tridy
  public int utratil;             // promenna instance

  public Zakaznik() {
    pocetZakazniku++;
    utratil = 0;
  }

  public void platil(int cena) {
    this.utratil += cena;
  }

  public static int kolikZakazniku() {
    return pocetZakazniku;
  }

  protected void finalize() throws Throwable {
    pocetZakazniku--;
    System.out.println("Konec zakaznika");
    super.finalize();
  }
}

public class Hlavni {
  public static void main(String[] args) {
    System.out.println("Pocet: " + Zakaznik.kolikZakazniku());
    Zakaznik zak1 = new Zakaznik();
    System.out.println("Pocet: " + Zakaznik.kolikZakazniku());
    Zakaznik zak2 = new Zakaznik();
    System.out.println("Pocet: " + Zakaznik.kolikZakazniku());
    zak1 = null;
    System.gc();
    System.runFinalization();
    System.out.println("Pocet: " + Zakaznik.kolikZakazniku());
  }
}
