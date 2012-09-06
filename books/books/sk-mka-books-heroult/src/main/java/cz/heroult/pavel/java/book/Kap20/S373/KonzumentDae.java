package cz.heroult.pavel.java.book.Kap20.S373;

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

public class KonzumentDae extends Thread {
  private CteniDae c;
  private int suma = 0;

  KonzumentDae(CteniDae c) {
    this.c = c;
  }

  public void run() {
    int cislo;
    while (true) {
      cislo = c.predej();
      if (interrupted() == false)
        suma += cislo;
      else
        break;
    }

    System.out.println("  " + c.jmenoSouboru + " - vysledna suma: " + suma);
  }
}
