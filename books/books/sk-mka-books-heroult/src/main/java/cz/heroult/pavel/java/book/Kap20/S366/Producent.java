package cz.heroult.pavel.java.book.Kap20.S366;

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

public class Producent extends Thread {
  private Cteni c;

  Producent(Cteni c) {
    this.c = c;
  }

  public void run() {
    while (interrupted() == false) {
      c.nacti();
    }
    System.out.print("  " + c.jmenoSouboru + " - konec cteni  ");
  }
}
