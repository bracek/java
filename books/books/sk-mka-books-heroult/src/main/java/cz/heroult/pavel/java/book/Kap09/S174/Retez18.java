package cz.heroult.pavel.java.book.Kap09.S174;

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

public class Retez18 {
  public static void main(String[] args) {
    int j = Integer.valueOf("1A2B", 16).intValue();
    System.out.println("j  = " + j);

    long l1 = Long.parseLong("12345");
    long l2 = Long.parseLong("1A2B", 16);
    double d = Double.parseDouble("123.5");
    float f = Float.parseFloat("1.235e2");
    System.out.println("l1 = " + l1);
    System.out.println("l2 = " + l2);
    System.out.println("d = " + d);
    System.out.println("f = " + f);
  }
}
