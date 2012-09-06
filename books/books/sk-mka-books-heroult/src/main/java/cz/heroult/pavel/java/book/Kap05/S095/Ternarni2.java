package cz.heroult.pavel.java.book.Kap05.S095;

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

public class Ternarni2 {
  public static void main(String[] args) {
    for (int i = 1;  i <= 100;  i++) {
      System.out.print(i);
      if (i % 10 == 0) {
        System.out.print("\n");
      }
      else
        System.out.print(" ");
    }

    System.out.println("---------------------");

    for (int i = 1;  i <= 100;  i++)
      System.out.print(i + ((i % 10 == 0) ? "\n" : " "));

  }
}
