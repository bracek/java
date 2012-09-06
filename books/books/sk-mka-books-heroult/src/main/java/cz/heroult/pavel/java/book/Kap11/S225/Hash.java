package cz.heroult.pavel.java.book.Kap11.S225;

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

class Pomocna {
  double d;
  Pomocna(double d) { this.d = d; }
}

public class Hash {
  public static void main(String[] args) {
    Pomocna p1 = new Pomocna(3.14);
    Pomocna p2 = new Pomocna(3.14);
    System.out.println("p1: " + p1.hashCode());
    System.out.println("p2: " + p2.hashCode());
    p1.d = 6.28;
    System.out.println("p1: " + p1.hashCode());

    Integer i1 = new Integer(5);
    Integer i2 = new Integer(5);
    Byte b = new Byte((byte) 5);
    System.out.println("i1: " + i1.hashCode());
    System.out.println("i2: " + i2.hashCode());
    System.out.println("b:  " + b.hashCode());
  }
}
