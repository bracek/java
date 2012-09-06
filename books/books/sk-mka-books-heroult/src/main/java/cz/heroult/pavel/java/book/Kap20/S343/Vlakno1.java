package cz.heroult.pavel.java.book.Kap20.S343;

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

public class Vlakno1 extends Thread {
  public Vlakno1(String jmeno) {
    super(jmeno);
  }
    @Override
  public void run() {
    for (int i = 1;  i <= 3;  i++) {
      System.out.println(i + ". " + getName());
      try {
        Thread.sleep(1000);
      }
      catch(InterruptedException e) {
        System.out.println("Jsem vzhuru - " + getName());
      }
    }
  }

  public static void main(String[] args) {
    Vlakno1 vlAhoj = new Vlakno1("ahoj");
    vlAhoj.start();
    new Vlakno1("nazdar").start();
    new Vlakno1("cao").start();
  }
}
