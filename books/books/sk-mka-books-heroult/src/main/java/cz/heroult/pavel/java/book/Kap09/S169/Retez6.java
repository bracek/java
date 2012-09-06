package cz.heroult.pavel.java.book.Kap09.S169;

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

public class Retez6 {
  public static void main(String[] args) {
    String s2, s1 = "cacao";

    s2 = s1.replace('c', 'k');
    System.out.println(s1);  // "cacao"
    System.out.println(s2);  // "kakao"
  }
}
