package cz.heroult.pavel.java.book.Kap13.S252;

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

class Usecka implements Info {
  int delka;

  Usecka(int delka) { this.delka = delka; }

  public void kdoJsem() {
    System.out.println("Usecka");
  }
}

class Obdelnik extends Usecka implements InfoOba {
  int sirka;

  Obdelnik(int delka, int sirka) {
    super(delka);
    this.sirka = sirka;
  }

  public void kdoJsem() {
    System.out.print(POCET + " Obdelnik");
  }

  public void vlastnosti() {
    System.out.println(" = " + delka + ", " + sirka);
  }

   public void vypisSirka() {  // není z žádného rozhraní
    System.out.println(sirka);
  }
}

public class Test {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);
    Obdelnik o = new Obdelnik(3, 6);
    if (u instanceof Info)
      System.out.println("u implementuje Info");
    if (o instanceof Info)
      System.out.println("o implementuje Info");
    if (u instanceof InfoOba)
      System.out.println("u implementuje InfoOba");
    if (o instanceof InfoOba)
      System.out.println("o implementuje InfoOba");
    if (u instanceof Usecka)
      System.out.println("u je instanci Usecka");
  }
}
