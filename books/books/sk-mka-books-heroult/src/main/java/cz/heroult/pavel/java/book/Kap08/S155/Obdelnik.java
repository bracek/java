package cz.heroult.pavel.java.book.Kap08.S155;

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

  public double delkaUhlopricky() {
    double pom;
    pom = (sirka * sirka) + (vyska * vyska);
    pom = Math.sqrt(pom);
    return pom;
  }

  public static void main(String[] args) {
    Obdelnik obd = new Obdelnik(6, 8);
    System.out.println("Uhlopricka je: " + obd.delkaUhlopricky());
  }
}
