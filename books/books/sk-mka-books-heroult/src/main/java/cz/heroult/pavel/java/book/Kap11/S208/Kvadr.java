package cz.heroult.pavel.java.book.Kap11.S208;

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

class Obdelnik {
  public int sirka;
  public int vyska;

  public Obdelnik(int sirka, int vyska) {
    this.sirka = sirka;
    this.vyska = vyska;
  }

  public double delkaUhlopricky() {
    double pom;
    pom = (sirka * sirka) + (vyska * vyska);
    return Math.sqrt(pom);
  }

  public int hodnotaSirky() {
    return sirka;
  }
}

public class Kvadr extends Obdelnik {
  public int hloubka;

  public Kvadr(int sirka, int vyska, int hloubka) {
    super(sirka, vyska);
    this.hloubka = hloubka;
  }

  public double delkaUhlopricky() {
    double pom = super.delkaUhlopricky();
    pom = (pom * pom) + (hloubka * hloubka);
    return Math.sqrt(pom);
  }

  public static void main(String[] args) {
    Kvadr kva = new Kvadr(6, 8, 10);
    System.out.println("Uhlopricka: "+ kva.delkaUhlopricky());
    System.out.println("Sirka je: " + kva.hodnotaSirky());
    System.out.println("Vyska je: " + kva.vyska);
  } 
}
