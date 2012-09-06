package cz.heroult.pavel.java.book.Kap15.S267;

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

class Jmeno {
  public void kdoJeTo(Object o) {
    System.out.print(o.getClass().getName());
  }
}

class Usecka {
  protected int delka;

  Usecka(int delka) { this.delka = delka; }
}

class Obdelnik extends Usecka implements Info {
  private int sirka;

  Obdelnik(int delka, int sirka) {
    super(delka);
    this.sirka = sirka;
  }
  
  public void kdoJsem() {
    new VnitrniJmeno().kdoJsem();
  }

  class VnitrniJmeno extends Jmeno {
    void kdoJsem() {
      kdoJeTo(Obdelnik.this);
//      kdoJeTo(this);
      System.out.println(" " + delka + "x" + sirka);
    }
  }
}

public class Test {
  public static void main(String[] args) {
    Info i = new Obdelnik(3, 6);
    i.kdoJsem();
  }
}
