package cz.heroult.pavel.java.book.Kap09.S186;

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

public class Charact1 {
  public static void main(String[] args) {
    System.out.println(Character.isDigit('1'));         // true
    System.out.println(Character.isDigit('\u0BE7'));    // true
    System.out.println(Character.isLetter('A'));        // true
    System.out.println(Character.isLetterOrDigit('?')); // false 
    System.out.println(Character.isLowerCase('b'));     // true
    System.out.println(Character.isUpperCase('B'));     // true
    System.out.println(Character.isWhitespace('\n'));   // true
  }
}
