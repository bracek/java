package cz.heroult.pavel.java.book.Kap09.S168;

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

public class Retez3 {
  public static void main(String[] args) {
    String s1, s2, s3, s4;
    s1 = new String("ahoj");
    s2 = new String("ahoi");
    s3 = new String("AHOJ");

    System.out.println("s1.compareTo(s2): " + s1.compareTo(s2));
    System.out.println("s2.compareTo(s1): " + s2.compareTo(s1));
    System.out.println("s1.compareToIgnoreCase(s3): " + s1.compareToIgnoreCase(s3));
    System.out.println("s1.equals(s3): " + s1.equals(s3));
    System.out.println("s1.equalsIgnoreCase(s3): " + s1.equalsIgnoreCase(s3));
  }
}
