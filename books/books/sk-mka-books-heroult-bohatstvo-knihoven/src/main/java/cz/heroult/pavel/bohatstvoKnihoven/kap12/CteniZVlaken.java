package cz.heroult.pavel.bohatstvoKnihoven.kap12;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.util.*;

public class CteniZVlaken extends Thread {
  static ArrayList<Integer> kol;  // pouze jedna kolekce

  CteniZVlaken(String jmeno) {
    super(jmeno);
  }

  public void run() {
    for (Integer i : kol) {
      System.out.println(getName() + ": " + i);
      Thread.yield();
    }
  }

  public static void main(String[] args) {
    kol = new ArrayList<Integer>();
    for (int i = 1;  i <= 4;  i++) {
      kol.add(new Integer(i));
    }

    new CteniZVlaken("1. vlakno").start();
    new CteniZVlaken("2. vlakno").start();
  }
}
