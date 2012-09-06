package cz.heroult.pavel.java.book.Kap05.S105;

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

public class ForContinue {
  public static void main(String[] args) {
navesti: 
    for (int n = 0;  n < 4;  n++) {
      for (int m = 0;  m < 2;  m++) {
        if (n == 2  &&  m == 0) 
          continue navesti;
        System.out.print(n + "-" + m + " ");
      }
    }
  }
}
