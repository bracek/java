package cz.heroult.pavel.java.book.Kap20.S359;

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

public class ReadVl extends Thread {
  FileReader fr;
  BufferedReader in;
  String jmenoSouboru;
  static public long suma = 0;
  static public boolean hotovo = false;

  ReadVl(String jmeno) {
    super("Vlakno pro cteni");
    jmenoSouboru = new String(jmeno);
  }

  public void run() {
    String radka;

    try {
      fr = new FileReader(jmenoSouboru);
      in = new BufferedReader(fr);
      while((radka = in.readLine()) != null) {
        suma += Integer.parseInt(radka);
        Thread.yield();
      }
      fr.close();
      hotovo = true;
    }
    catch (IOException e) {
      System.out.println("Chyba v souboru!");
      hotovo = true;
     }
  }
}
