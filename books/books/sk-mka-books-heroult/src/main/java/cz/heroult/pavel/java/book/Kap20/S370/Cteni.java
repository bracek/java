package cz.heroult.pavel.java.book.Kap20.S370;

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

public class Cteni {
  private FileReader fr;
  private BufferedReader in;
  public String jmenoSouboru;
  private int hodnota;
  private boolean precteno = false;
  private boolean konecSouboru = false;

  Cteni(String jmeno) {
    jmenoSouboru = new String(jmeno);
    try {
      fr = new FileReader(jmenoSouboru);
      in = new BufferedReader(fr);
    }
    catch (IOException e) {
      System.out.println("Chyba pri otvirani souboru!");
    }
  }

  synchronized public void nacti() {
    while (precteno == true) {
      try {
        wait();
      }
      catch (InterruptedException e) { }
    }

    String radka;
    try {
      if ((radka = in.readLine()) != null) {
        hodnota = Integer.valueOf(radka).intValue();
        System.out.print(jmenoSouboru + " precteno: " + hodnota + "  ");
      }
      else {
        konecSouboru = true;
        Thread.currentThread().interrupt();
      }
    }
    catch (IOException e) {
      System.out.println("Chyba pri cteni souboru!");
    }
    precteno = true;
    notifyAll();
  }

  synchronized public int predej() {
    while (precteno == false) {
      try {
        wait();
      }
      catch (InterruptedException e) { }
    }
    precteno = false;
    if (konecSouboru == false) {
      System.out.println(jmenoSouboru + " predano: " + hodnota + "  ");
      notifyAll();
      return hodnota;
    }
    else {
      Thread.currentThread().interrupt();
      return 0;
    }
  }

  protected void finalize() {
    try {
      fr.close();
    }
    catch (IOException e) {
      System.out.println("Chyba pri zavirani souboru!");
    }
  }
}
