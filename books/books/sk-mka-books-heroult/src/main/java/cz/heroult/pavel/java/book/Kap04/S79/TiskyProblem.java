package cz.heroult.pavel.java.book.Kap04.S79;

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

public class TiskyProblem {
  public static void main(String[] args) {
   char c = '\n'; 
   int i = 5;

   System.out.println(i + c);
   System.out.println(i + '\n');
   System.out.println("i " + i + '\n');
   System.out.println(i + '\n' + " i");
  }
}
