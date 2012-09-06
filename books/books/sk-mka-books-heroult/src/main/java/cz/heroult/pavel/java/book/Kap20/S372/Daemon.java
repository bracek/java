package cz.heroult.pavel.java.book.Kap20.S372;

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

public class Daemon extends Thread {
  public void run() {
    for (int i = 1;  i <= 10;  i++) {
      System.out.println(i + ". daemon je tu");
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e) {
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Zacatek programu");
    Daemon vlD = new Daemon();
//    vlD.setDaemon(true);
    vlD.start();
    if (vlD.isDaemon() == true)
      System.out.println("Program hned skonci");
    else
      System.out.println("Program pobezi dlouho");

    System.out.println("Konec programu");
  }
}
