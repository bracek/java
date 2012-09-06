package cz.heroult.pavel.java.book.Kap11.S223;

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

public class Rovnost {
  public static void main(String[] args) {
    Pomocna p1 = new Pomocna(3.14);
    Pomocna p2 = new Pomocna(3.14);
    if (p1.equals(p2) == false)
      System.out.println("p1 != p2");

    Pomocna p3 = p1;
    if (p1.equals(p3) == true)
      System.out.println("p1 == p3");

    Double d1 = new Double(3.14);
    Double d2 = new Double(3.14);
    if (d1.equals(d2) == true)
      System.out.println("d1 == d2");
  }
}
