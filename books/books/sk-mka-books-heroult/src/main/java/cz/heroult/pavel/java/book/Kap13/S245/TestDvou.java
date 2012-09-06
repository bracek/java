package cz.heroult.pavel.java.book.Kap13.S245;

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

class Usecka implements Info, InfoDalsi {
  int delka;
  Usecka(int delka) { this.delka = delka; }
  public void kdoJsem() {
    System.out.print("Usecka");
  }
  public void vlastnosti() {
    System.out.println(" = " + delka);
  }
}

public class TestDvou {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);
    u.kdoJsem(); 
    u.vlastnosti();
  }
}
