package cz.heroult.pavel.java.book.Kap20.S354;

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

public class Vlakno5 implements Runnable {
  private Thread zobrazVl = null;

  public void start() {
    zobrazVl = new Thread(this);
    zobrazVl.start();
  }

  public void run() {
    while (ReadVl.hotovo == false) {
      System.out.print(ReadVl.suma + "\r");
      try {
        Thread.sleep(100);  // 100 milisekund
      }
      catch (InterruptedException e) {
      }
    }
    System.out.println(ReadVl.suma);
  }

  public static void main(String[] args) {
    ReadVl vlCteni = new ReadVl("data.txt");
    vlCteni.start();
    Vlakno5 vlVypis = new Vlakno5();
    vlVypis.start();
  }
}
