package cz.heroult.pavel.java.book.Kap05.S103;

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

public class ForCarka2 {
  public static void main(String[] args) {
    int i, sum;

    for (i = 1, sum = 0;  i <= 10;  i++)
      sum += i;

    System.out.println("suma = " + sum);
  }
}
