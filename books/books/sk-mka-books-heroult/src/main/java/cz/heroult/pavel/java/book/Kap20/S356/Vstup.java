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

import java.io.*;

public class Vstup extends Thread {
  static public boolean hotovo = false;
  public void run() {
    byte[] pole = new byte[10];
    Thread.currentThread().setPriority(MAX_PRIORITY);
    while (hotovo == false) {
      try {
        System.in.read(pole);
        if (pole[0] == 'K') {
          hotovo = true;
        }
      }
      catch (IOException e) {
        System.out.println("Chyba vstupu");
        hotovo = true;
      }
    }
  }
}
