package cz.heroult.pavel.java.book.Kap03.S67;

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

public class UplnyLogickySoucin {
  public static void main(String[] args) {
    int i, j, k;

    i = 1; j = 2; k = 3;
    if (i == 2  &  ++j == 3)
      k = 4;
    System.out.println("i = "+ i +", j = "+ j +", k = "+ k);
  }
}
