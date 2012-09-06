package cz.heroult.pavel.java.book.Kap09.S181;

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

public class MujString {
  int hodnota;

  MujString(int h) { hodnota = h; }

  public String toString() {  // public je nutny
     String jmenoTridy = new String(getClass().getName());
     return (jmenoTridy + ": " + hodnota);
  }
  void puvodniToString() {
    System.out.println(super.toString());
  }

  public static void main(String[] args) {
    MujString s1 = new MujString(5);
    s1.puvodniToString();
    System.out.println(s1.toString());
  }
}
