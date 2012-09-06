package cz.heroult.pavel.java.book.Kap20.S356;

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

public class Vlakno7 extends Thread {
  public void run() {
    long i = 0;
    while (Vstup.hotovo == false) {
      System.out.print(i++ + "\r");
    }
  }

  public static void main(String[] args) {
    Vstup vlVstup = new Vstup();
    vlVstup.start();
    Vlakno7 vlVypis = new Vlakno7();
    vlVypis.start();
  }
}
