package cz.heroult.pavel.java.book.Kap09.S187;

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

public class Charact3 {
  public static void main(String[] args) {
    int i, j;
    i = Character.digit('5', 10);
    j = Character.digit('F', 16);
    System.out.println("i = " + i + " j = " + j);   // i = 5 j = 15
    i = Character.digit('\u0BE7', 10);              // tamilská jednièka
    System.out.println("i = " + i);                 // i = 1
  }
}
