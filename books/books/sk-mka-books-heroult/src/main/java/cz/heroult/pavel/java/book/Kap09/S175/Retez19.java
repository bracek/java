package cz.heroult.pavel.java.book.Kap09.S175;

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

public class Retez19 {
  public static void main(String[] args) {
    String s1 = "\r\n\t cacao\t \r\n";
    int i;

    i = s1.trim().toUpperCase().substring(2).indexOf('O');
    System.out.println("O je " + (i + 1) + ".znak");
  }
}
