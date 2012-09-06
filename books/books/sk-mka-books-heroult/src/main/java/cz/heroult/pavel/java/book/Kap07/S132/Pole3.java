package cz.heroult.pavel.java.book.Kap07.S132;

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

public class Pole3 {
  public static void main(String[] args) {
    int[] prvocisla = {1, 2, 3, 5, 7, 11};

    for (int i = 0;  i < prvocisla.length;  i++) {
      System.out.print(prvocisla[i] + " -> ");
      prvocisla[i] = i + 1;
      System.out.print(prvocisla[i] + "  ");
    }
  }
}
