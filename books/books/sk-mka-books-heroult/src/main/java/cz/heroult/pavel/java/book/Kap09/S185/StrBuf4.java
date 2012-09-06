package cz.heroult.pavel.java.book.Kap09.S185;

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

public class StrBuf4 {
  public static void main(String[] args) {
    StringBuffer b;

    b = new StringBuffer("Ahoj");

    String s1, s2, s3;

    s1 = b.toString();
    s2 = b.substring(1);
    s3 = b.substring(1, 3);
    System.out.println(s1);  // Ahoj
    System.out.println(s2);  // hoj
    System.out.println(s3);  // ho
  }
}
