package cz.heroult.pavel.java.book.Kap20.S353;

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

public class VypisoveVlakno extends Thread {
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

  public static void main(String[] argv) throws InterruptedException {
    ReadVl vlCteni = new ReadVl("data.txt");

// pøi pokusech postupnì odkomentovávat
//    vlCteni.setPriority(MIN_PRIORITY);
//    vlCteni.setPriority(MAX_PRIORITY);
    vlCteni.start();
    VypisoveVlakno vlVypis = new VypisoveVlakno();
    vlVypis.start();
  }
}
