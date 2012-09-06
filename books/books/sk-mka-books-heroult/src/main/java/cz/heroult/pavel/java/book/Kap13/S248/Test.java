package cz.heroult.pavel.java.book.Kap13.S248;

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

class Obdelnik extends Usecka {
  int sirka;

  Obdelnik(int delka, int sirka) {
    super(delka);
    this.sirka = sirka;
  }

  public void kdoJsem() {
    System.out.println("Obdelnik");
  }
}

public class Test {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);
    Obdelnik o = new Obdelnik(2, 4);
    Info iu = new Usecka(6);
    Info io = new Obdelnik(3, 6);
    u.kdoJsem();
    o.kdoJsem();
    iu.kdoJsem();
    io.kdoJsem();
  } 
}   
