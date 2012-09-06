package cz.heroult.pavel.java.book.Kap09.S183;

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

public class StrBuf1 {
  public static void main(String[] args) {
    StringBuffer b1, b2, b3;

    b1 = new StringBuffer();
    b2 = new StringBuffer(100);
    b3 = new StringBuffer("Ahoj");

    System.out.println(b1.length() +" " + b1.capacity()); // 0 16
    System.out.println(b2.length() +" " + b2.capacity()); // 0 100
    System.out.println(b3.length() +" " + b3.capacity()); // 4 20

    b1.setLength(18);
    b2.ensureCapacity(110);
    b3.setLength(3);

    System.out.println(b1.length() +" " + b1.capacity()); // 18 34
    System.out.println(b2.length() +" " + b2.capacity()); // 0 202 
    System.out.println(b3.length() +" " + b3.capacity()); // 3 20   
  }
}
