package cz.heroult.pavel.java.book.Kap15.S263;

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
    return new UseckaInfo();
  }

  class UseckaInfo implements Info {
    public void kdoJsem() {
      System.out.println("Usecka " + delka);
    }
  }
}

public class Test {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);

//    u.kdoJsem();   // nelze
//    Info i = u;    // nelze
    Info i = u.informace();
    i.kdoJsem();
  }
}
