package cz.heroult.pavel.java.book.Kap04.S84;

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

public class VypisRetezec {
  public static void main(String[] args) {
    String s = "Dobrý den";
    System.out.format("s = %s%n", s); // s = Dobrý den
    System.out.format("s = %S%n", s); // s = DOBRÝ DEN
    System.out.format("s = |%11s|%n", s);  // s = |  Dobrý den|
    System.out.format("s = |%-11s|%n", s); // s = |Dobrý den  |
  }
}
 