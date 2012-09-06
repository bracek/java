package cz.heroult.pavel.java.book.Kap15.S266;

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

  public Info i = new Info() {
    public void kdoJsem() {
      System.out.println("Usecka " + delka);
    }
  };    // konec deklarace promenne i
}

public class Test {
  public static void main(String[] args) {
    Usecka u = new Usecka(5);
    u.i.kdoJsem();

    if (u instanceof Info)
      System.out.println("u implementuje Info");
    if (u.i instanceof Info)
      System.out.println("u.i implementuje Info");
    if (u.i instanceof Info)
      System.out.println(u.i.getClass().getName() +
                         " implementuje Info");
  }
}
