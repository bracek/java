package cz.heroult.pavel.java.book.Kap15.S265;

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

class Usecka {
  int delka;
  Usecka(int delka) { this.delka = delka; }

  public Info informace() {
    return new Info() {
      public void kdoJsem() {
        System.out.println("Usecka " + delka);
      }
    };    // konec prikazu return - støedník nutný
  }       // konec metody informace()
}

public class Test {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);
//    u.kdoJsem();                 // chyba
    Info i = u.informace();
    i.kdoJsem();
  }
}
